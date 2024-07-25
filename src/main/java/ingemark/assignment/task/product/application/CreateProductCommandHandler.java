package ingemark.assignment.task.product.application;

import ingemark.assignment.task.product.application.command.CreateProductCommand;
import ingemark.assignment.task.product.domain.*;
import ingemark.assignment.task.product.domain.event.ProductCreatedEvent;
import ingemark.assignment.task.shared.common.application.AbstractCommandHandler;
import ingemark.assignment.task.shared.common.application.result.CreateCommandResult;
import ingemark.assignment.task.shared.common.infrastructure.date.DateUtil;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.AlreadyExistsException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.NotSavedException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;
import ingemark.assignment.task.shared.hnb.HnbExchangeRateResponse;
import ingemark.assignment.task.shared.hnb.HnbUtil;
import ingemark.assignment.task.shared.id.Id;
import ingemark.assignment.task.shared.name.Name;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;


@Component
public class CreateProductCommandHandler extends AbstractCommandHandler<CreateProductCommand, CreateCommandResult<Product>> {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final HnbUtil hnbUtil;

    public CreateProductCommandHandler(ProductRepository productRepository, 
                                       ApplicationEventPublisher applicationEventPublisher,
                                       HnbUtil hnbUtil) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.hnbUtil = hnbUtil;
    }

    public CreateCommandResult<Product> handle(CreateProductCommand command) {
        if (productRepository.isCodeExists(Code.of(command.code()))) {
            throw AlreadyExistsException.alreadyExistsException(ErrorSpecification.PRODUCT_NOT_SAVED);
        }

        PriceUsd priceUsd = getUsdPrice(PriceEur.of(command.euroPrice()));

        Product product = Product.builder()
                .id(Id.random())
                .name(Name.of(command.name()))
                .code(Code.of(command.code()))
                .priceEur(PriceEur.of(command.euroPrice()))
                .priceUsd(priceUsd)
                .isAvailable(IsAvailable.of(command.isAvailable()))
                .build();

        Product saved = productRepository.save(product)
                .orElseThrow(() -> NotSavedException.notSavedException(
                        ErrorSpecification.PRODUCT_NOT_SAVED));

        applicationEventPublisher.publishEvent(ProductCreatedEvent.of(saved.getId()));
        return new CreateCommandResult<>(saved);
    }

    private PriceUsd getUsdPrice (PriceEur euroPrice){
        Optional<HnbExchangeRateResponse> exchangeRate = hnbUtil.getExchangeRate(DateUtil.now().toLocalDate(), "USD");
        if (exchangeRate.isEmpty()){
            return null;
        }

        BigDecimal middleExchange =  exchangeRate.get().getSrednjiTecaj();
        BigDecimal usdPrice = euroPrice.value().multiply(middleExchange);

        return PriceUsd.of(usdPrice);
    }
}
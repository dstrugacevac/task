package ingemark.assignment.task.product.application;


import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.product.domain.ProductRepository;
import ingemark.assignment.task.shared.common.application.AbstractCommandHandler;
import ingemark.assignment.task.shared.common.application.command.FindByIdCommand;
import ingemark.assignment.task.shared.common.application.result.FindByIdCommandResult;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.NotFoundByIdException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;
import ingemark.assignment.task.shared.id.Id;
import org.springframework.stereotype.Component;

@Component
public class FindByIdProductCommandHandler
        extends AbstractCommandHandler<FindByIdCommand, FindByIdCommandResult<Product>> {

    private final ProductRepository productRepository;

    public FindByIdProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public FindByIdCommandResult<Product> handle(FindByIdCommand command) {
        Product product = productRepository.findById(Id.of(command.id()))
                .orElseThrow(() -> NotFoundByIdException.notFoundByIdException(
                        command.id(),
                        ErrorSpecification.PRODUCT_NOT_FOUND));

        return new FindByIdCommandResult<>(product);
    }
}

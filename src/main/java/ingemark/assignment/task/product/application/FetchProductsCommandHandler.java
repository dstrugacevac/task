package ingemark.assignment.task.product.application;


import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.product.domain.ProductRepository;
import ingemark.assignment.task.shared.common.application.AbstractCommandHandler;
import ingemark.assignment.task.shared.common.application.command.FetchCommand;
import ingemark.assignment.task.shared.common.application.result.FetchCommandResult;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FetchProductsCommandHandler extends AbstractCommandHandler<FetchCommand, FetchCommandResult<Product>> {

    private final ProductRepository productRepository;

    public FetchProductsCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public FetchCommandResult<Product> handle(FetchCommand command) {
        List<Product> certificateDefinitions = productRepository.fetch(command.pageable());
        Long total = productRepository.total();
        return new FetchCommandResult<>(certificateDefinitions, total);
    }
}

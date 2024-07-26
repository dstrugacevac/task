package ingemark.assignment.task.product.application;

import ingemark.assignment.task.product.application.command.CreateProductCommand;
import ingemark.assignment.task.product.domain.Code;
import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.product.domain.ProductRepository;
import ingemark.assignment.task.product.domain.event.ProductCreatedEvent;
import ingemark.assignment.task.shared.common.application.command.FetchCommand;
import ingemark.assignment.task.shared.common.application.command.FindByIdCommand;
import ingemark.assignment.task.shared.common.application.result.FindByIdCommandResult;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.AlreadyExistsException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.NotFoundByIdException;
import ingemark.assignment.task.shared.hnb.HnbExchangeRateResponse;
import it.unibo.tuprolog.solve.stdlib.primitive.Op;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static ingemark.assignment.task.product.ProductTestHelper.createProductCommand;
import static ingemark.assignment.task.product.ProductTestHelper.mockProduct;
import static ingemark.assignment.task.shared.hnb.HnbTestHelper.mockHnbExchangeRateResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(SpringExtension.class)
public class FindByIdProductCommandHandlerTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private FindByIdProductCommandHandler handler;

    @Test
    void shouldFindByIdSuccessfully() {
        Product product = mockProduct();

        FindByIdCommand command = new FindByIdCommand(product.getId().id());

        when(productRepositoryMock.findById(any())).thenReturn(Optional.of(product));

        FindByIdCommandResult<Product> result = handler.handle(command);

        assertThat(result.model().getId().id()).isEqualTo(product.getId().id());
        assertThat(result.model().getName().value()).isEqualTo(product.getName().value());

        verify(productRepositoryMock, times(1)).findById(any());
    }

    @Test
    void shouldThrowNotFoundException() {
        Product product = mockProduct();
        FindByIdCommand command = new FindByIdCommand(product.getId().id());

        when(productRepositoryMock.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundByIdException.class, () -> handler.handle(command));
    }
}

package ingemark.assignment.task.product.application;

import ingemark.assignment.task.product.application.command.CreateProductCommand;
import ingemark.assignment.task.product.domain.Code;
import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.product.domain.ProductRepository;
import ingemark.assignment.task.product.domain.event.ProductCreatedEvent;
import ingemark.assignment.task.shared.common.application.result.CreateCommandResult;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.AlreadyExistsException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.NotSavedException;
import ingemark.assignment.task.shared.hnb.HnbExchangeRateResponse;
import ingemark.assignment.task.shared.hnb.HnbUtil;
import it.unibo.tuprolog.solve.stdlib.primitive.Op;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static ingemark.assignment.task.product.ProductTestHelper.createProductCommand;
import static ingemark.assignment.task.product.ProductTestHelper.mockProduct;
import static ingemark.assignment.task.shared.hnb.HnbTestHelper.mockHnbExchangeRateResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;


@ExtendWith(SpringExtension.class)
public class CreateProductCommandHandlerTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private ApplicationEventPublisher applicationEventPublisherMock;

    @Mock
    private HnbUtil hnbUtil;
    @InjectMocks
    private CreateProductCommandHandler handler;

    @Test
    void shouldCreateSuccessfully() {
        CreateProductCommand command = createProductCommand();
        Product expectedProduct = mockProduct();
        HnbExchangeRateResponse hnbExchangeRateResponse = mockHnbExchangeRateResponse();

        when(productRepositoryMock.isCodeExists(Code.of(command.code()))).thenReturn(false);
        when(productRepositoryMock.save(any())).thenReturn(Optional.of(expectedProduct));
        when(hnbUtil.getExchangeRate(any(), any())).thenReturn(Optional.of(hnbExchangeRateResponse));
        CreateCommandResult<Product> result = handler.handle(command);

        assertThat(result.model().getId().id()).isEqualTo(expectedProduct.getId().id());
        assertThat(result.model().getName().value()).isEqualTo(expectedProduct.getName().value());

        verify(productRepositoryMock, times(1)).isCodeExists(Code.of(command.code()));
        verify(productRepositoryMock, times(1)).save(any());
        verify(applicationEventPublisherMock, times(1)).publishEvent(any(ProductCreatedEvent.class));
    }

    @Test
    void shouldThrowAlreadyExistsExceptionForExistingCode() {
        CreateProductCommand command = createProductCommand();
        Product expectedProduct = mockProduct();
        HnbExchangeRateResponse hnbExchangeRateResponse = mockHnbExchangeRateResponse();

        when(productRepositoryMock.isCodeExists(Code.of(command.code()))).thenReturn(true);
        when(productRepositoryMock.save(any())).thenReturn(Optional.of(expectedProduct));
        when(hnbUtil.getExchangeRate(any(), any())).thenReturn(Optional.of(hnbExchangeRateResponse));

        assertThrows(AlreadyExistsException.class, () -> handler.handle(command));

        verify(productRepositoryMock, times(1)).isCodeExists(Code.of(command.code()));
        verify(productRepositoryMock, times(0)).save(any());
        verify(applicationEventPublisherMock, times(0)).publishEvent(any(ProductCreatedEvent.class));
    }

    @Test
    void shouldThrowNotSavedException() {
        CreateProductCommand command = createProductCommand();
        HnbExchangeRateResponse hnbExchangeRateResponse = mockHnbExchangeRateResponse();

        when(productRepositoryMock.isCodeExists(Code.of(command.code()))).thenReturn(false);
        when(productRepositoryMock.save(any())).thenReturn(Optional.empty());
        when(hnbUtil.getExchangeRate(any(), any())).thenReturn(Optional.of(hnbExchangeRateResponse));

        assertThrows(NotSavedException.class, () -> handler.handle(command));

        verify(productRepositoryMock, times(1)).isCodeExists(Code.of(command.code()));
        verify(productRepositoryMock, times(1)).save(any());
        verify(applicationEventPublisherMock, times(0)).publishEvent(any(ProductCreatedEvent.class));
    }
}
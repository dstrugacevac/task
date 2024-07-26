package ingemark.assignment.task.product.application;

import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.product.domain.ProductRepository;
import ingemark.assignment.task.shared.common.application.command.FetchCommand;
import ingemark.assignment.task.shared.hnb.HnbUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static ingemark.assignment.task.product.ProductTestHelper.mockProduct;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class FetchProductsCommandHandlerTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private FetchProductsCommandHandler handler;

    @Test
    void shouldFetchSuccessfully() {

        List<Product> products = List.of(mockProduct());
        Long expectedTotal = 1L;

        FetchCommand command = new FetchCommand(Pageable.ofSize(10));

        when(productRepositoryMock.fetch(any())).thenReturn(products);
        when(productRepositoryMock.total()).thenReturn(1L);

        var result = handler.handle(command);

        assertThat(result.elements().get(0).getId().id()).isEqualTo(products.get(0).getId().id());
        assertThat(result.elements().get(0).getName().value()).isEqualTo(products.get(0).getName().value());
        assertThat(result.total()).isEqualTo(expectedTotal);

        verify(productRepositoryMock, times(1)).fetch(any());
        verify(productRepositoryMock, times(1)).total();
    }
}

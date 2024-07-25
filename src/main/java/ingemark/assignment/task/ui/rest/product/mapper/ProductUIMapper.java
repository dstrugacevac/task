package ingemark.assignment.task.ui.rest.product.mapper;

import ingemark.assignment.task.product.application.command.CreateProductCommand;
import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.shared.common.application.result.FetchCommandResult;
import ingemark.assignment.task.ui.rest.product.request.CreateProductRequest;
import ingemark.assignment.task.ui.rest.product.response.ProductResponse;
import ingemark.assignment.task.ui.rest.shared.mapper.MapperExtension;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductUIMapper extends MapperExtension {
    CreateProductCommand toCommand(CreateProductRequest request);

    @Mapping(target = "id", expression = "java(mapIdentifier(product == null ? null : product.getId()))")
    @Mapping(target = "code", source = "product.code.value")
    @Mapping(target = "name", source = "product.name.value")
    @Mapping(target = "euroPrice", source = "product.priceEur.value")
    @Mapping(target = "usdPrice", source = "product.priceUsd.value")
    @Mapping(target = "isAvailable", source = "product.isAvailable.value")
    ProductResponse toResponse(Product product);

    default Page<ProductResponse> toResponse(
            FetchCommandResult<Product> result,
            Pageable pageable) {

        List<ProductResponse> responseContent = Optional.ofNullable(result.elements())
                .orElse(Collections.emptyList()).stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(responseContent, pageable, result.total());
    }
}

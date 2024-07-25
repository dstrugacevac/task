package ingemark.assignment.task.product.domain;

import ingemark.assignment.task.shared.common.domain.Aggregate;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.DefinitionNotValidException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;
import ingemark.assignment.task.shared.id.Id;
import ingemark.assignment.task.shared.name.Name;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;


@ToString
@Builder
@Getter

public class Product extends Aggregate<Product> {

    private final Id id;
    private Code code;
    private Name name;
    private PriceEur priceEur;
    private PriceUsd priceUsd;
    private IsAvailable isAvailable;

    private Product(Id id, Code code, Name name, PriceEur priceEur, PriceUsd priceUsd, IsAvailable isAvailable) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.priceEur = priceEur;
        this.priceUsd = priceUsd;
        this.isAvailable = isAvailable;

        validate();
    }

    private void validate() {
        if (Objects.isNull(id) || Objects.isNull(code) || Objects.isNull(name) || Objects.isNull(priceEur)
                || Objects.isNull(priceUsd) || Objects.isNull(isAvailable)) {
            throw DefinitionNotValidException.definitionNotValidException(
                    this,
                    ErrorSpecification.PRODUCT_DEFINITION_NOT_VALID);
        }
    }
}

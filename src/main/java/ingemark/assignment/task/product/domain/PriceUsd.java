package ingemark.assignment.task.product.domain;

import ingemark.assignment.task.shared.common.domain.ValueObject;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.ValueNotValidException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

import java.math.BigDecimal;
import java.util.Objects;

public record PriceUsd(BigDecimal value) implements ValueObject<PriceUsd> {

    public PriceUsd(BigDecimal value) {
        this.value = value;

        validateAndThrow();
    }

    public static PriceUsd of(BigDecimal value) {
        return new PriceUsd(value);
    }

    public boolean isValid() {
        return Objects.nonNull(value) && value.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public LogicalException getValidationException() {
        return ValueNotValidException.valueNotValidException(value, ErrorSpecification.PRICE_NOT_VALID);
    }
}
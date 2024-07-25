package ingemark.assignment.task.product.domain;

import ingemark.assignment.task.shared.common.domain.ValueObject;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.ValueNotValidException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

import static java.util.Objects.nonNull;


public record IsAvailable(Boolean value) implements ValueObject<IsAvailable> {

    public IsAvailable(Boolean value) {
        this.value = value;

        validateAndThrow();
    }

    public static IsAvailable of(Boolean value) {
        return new IsAvailable(value);
    }

    @Override
    public boolean isValid() {
        return nonNull(value);
    }

    @Override
    public LogicalException getValidationException() {
        return ValueNotValidException.valueNotValidException(value, ErrorSpecification.IS_AVAILABLE_NOT_VALID);
    }
}
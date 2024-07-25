package ingemark.assignment.task.shared.name;

import ingemark.assignment.task.shared.common.domain.ValueObject;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.ValueNotValidException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

import static java.util.Objects.nonNull;

public record Name(String value) implements ValueObject<Name> {

    public Name(String value) {
        this.value = value;

        validateAndThrow();
    }

    public static Name of(String value) {
        return new Name(value);
    }

    @Override
    public boolean isValid() {
        return nonNull(value) && !value.isBlank();
    }

    @Override
    public LogicalException getValidationException() {
        return ValueNotValidException.valueNotValidException(value, ErrorSpecification.NAME_NOT_VALID);
    }
}

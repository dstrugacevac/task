package ingemark.assignment.task.product.domain;


import ingemark.assignment.task.shared.common.domain.ValueObject;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.ValueNotValidException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

public record Code(String value) implements ValueObject<Code> {

    public Code(String value) {
        this.value = value;

        validateAndThrow();
    }

    public static Code of(String code) {
        return new Code(code);
    }

    @Override
    public boolean isValid() {
        return value != null && value.length()==10;
    }

    @Override
    public LogicalException getValidationException() {
        return ValueNotValidException.valueNotValidException(value, ErrorSpecification.CODE_VALUE_NOT_VALID);
    }
}

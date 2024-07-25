package ingemark.assignment.task.shared.common.infrastructure.error.exception;

import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

public class ValueNotValidException extends LogicalException {

    private ValueNotValidException(Object invalidValue, ErrorSpecification errorSpecification) {
        super("Given Value not valid. Value is [%s]".formatted(invalidValue), errorSpecification);
    }

    private ValueNotValidException(String message, ErrorSpecification errorSpecification) {
        super(message, errorSpecification);
    }


    public static ValueNotValidException valueNotValidException(
            Object invalidValue,
            ErrorSpecification errorSpecification) {

        return new ValueNotValidException(invalidValue, errorSpecification);
    }

    public static ValueNotValidException valueNotValidException(
            String message,
            ErrorSpecification errorSpecification) {

        return new ValueNotValidException(message, errorSpecification);
    }
}

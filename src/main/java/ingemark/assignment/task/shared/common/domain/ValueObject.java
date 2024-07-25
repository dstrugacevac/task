package ingemark.assignment.task.shared.common.domain;


import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;

public interface ValueObject<SELF> {

    default boolean isValid() {
        return true;
    }

    default boolean validateAndThrow() {
        if (!isValid()) {
            throw getValidationException();
        }

        return true;
    }

    LogicalException getValidationException();

}

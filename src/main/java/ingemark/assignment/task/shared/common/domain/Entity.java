package ingemark.assignment.task.shared.common.domain;

import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;

public abstract class Entity<SELF> {

    protected boolean isValid() {
        return true;
    }

    protected boolean validateAndThrow() {
        if (!isValid()) {
            throw getValidationException();
        }

        return true;
    }

    protected abstract LogicalException getValidationException();

}

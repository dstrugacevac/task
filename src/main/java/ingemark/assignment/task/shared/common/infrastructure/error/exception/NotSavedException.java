package ingemark.assignment.task.shared.common.infrastructure.error.exception;


import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

public class NotSavedException extends LogicalException {

    private NotSavedException(ErrorSpecification errorSpecification) {
        super("Not saved", errorSpecification);
    }

    public static NotSavedException notSavedException(ErrorSpecification errorSpecification) {
        return new NotSavedException(errorSpecification);
    }

}

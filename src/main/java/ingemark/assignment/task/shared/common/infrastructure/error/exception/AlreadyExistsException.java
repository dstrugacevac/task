package ingemark.assignment.task.shared.common.infrastructure.error.exception;

import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;
import ingemark.assignment.task.shared.id.Id;

import java.util.UUID;

public class AlreadyExistsException extends LogicalException {

    private static Id id;

    private AlreadyExistsException(UUID id, ErrorSpecification errorSpecification) {
        super("Already exists with id: " + id, errorSpecification);
    }

    private AlreadyExistsException(String code, ErrorSpecification errorSpecification) {
        super("Already exists with: " + code, errorSpecification);
    }

    private AlreadyExistsException(ErrorSpecification errorSpecification) {
        super("Already exists with same attributes.", errorSpecification);
    }


    public static AlreadyExistsException alreadyExistsException(UUID id, ErrorSpecification errorSpecification) {
        return new AlreadyExistsException(id, errorSpecification);
    }

    public static AlreadyExistsException alreadyExistsException(String code, ErrorSpecification errorSpecification) {
        return new AlreadyExistsException(code, errorSpecification);
    }

    public static AlreadyExistsException alreadyExistsException(ErrorSpecification errorSpecification) {
        return new AlreadyExistsException(errorSpecification);
    }
}

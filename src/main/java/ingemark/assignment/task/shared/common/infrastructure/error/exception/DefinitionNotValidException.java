package ingemark.assignment.task.shared.common.infrastructure.error.exception;

import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

public class DefinitionNotValidException extends LogicalException {

    private DefinitionNotValidException(Object invalidDomain, ErrorSpecification errorSpecification) {
        super("Domain definition not valid. Domain is [%s]".formatted(invalidDomain), errorSpecification);
    }

    public static DefinitionNotValidException definitionNotValidException(
            Object invalidDomain,
            ErrorSpecification errorSpecification) {

        return new DefinitionNotValidException(invalidDomain, errorSpecification);
    }
}

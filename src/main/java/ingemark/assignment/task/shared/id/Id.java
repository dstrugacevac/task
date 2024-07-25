package ingemark.assignment.task.shared.id;


import ingemark.assignment.task.shared.common.domain.Identifier;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.exception.ValueNotValidException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.nonNull;

public record Id(UUID id) implements Identifier<Id, UUID> {

    public Id(UUID id) {
        this.id = id;

        validateAndThrow();
    }

    public static Id random() {
        return new Id(UUID.randomUUID());
    }

    public static Id of(UUID id) {
        return new Id(id);
    }

    public static Id of(String id) {
        return new Id(UUID.fromString(id));
    }

    @Override
    public boolean isValid() {
        return nonNull(id);
    }

    @Override
    public LogicalException getValidationException() {
        return ValueNotValidException.valueNotValidException(id, ErrorSpecification.ID_NOT_VALID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Id id1 = (Id) o;
        return id.equals(id1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package ingemark.assignment.task.shared.common.infrastructure.error.exception;

import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;
import ingemark.assignment.task.shared.id.Id;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

public class NotFoundByIdException extends LogicalException {

    private static Id id;

    private NotFoundByIdException(UUID id, ErrorSpecification errorSpecification) {
        super("Not found. Given id: " + id, errorSpecification);
    }

    private NotFoundByIdException(String id, ErrorSpecification errorSpecification) {
        super("Not found. Given id: " + id, errorSpecification);
    }

    public static NotFoundByIdException notFoundByIdException(Id id, ErrorSpecification errorSpecification) {
        return new NotFoundByIdException(isNull(id) ? null : id.id(), errorSpecification);
    }

    public static NotFoundByIdException notFoundByIdException(UUID id, ErrorSpecification errorSpecification) {
        return new NotFoundByIdException(id, errorSpecification);
    }

    public static NotFoundByIdException notFoundByIdException(String id, ErrorSpecification errorSpecification) {
        return new NotFoundByIdException(id, errorSpecification);
    }

    public static NotFoundByIdException notFoundByIdException(List<UUID> ids, ErrorSpecification errorSpecification) {
        var idsString = ids == null ? "null" : ids.toString();
        return new NotFoundByIdException(idsString, errorSpecification);
    }

    public static NotFoundByIdException notFoundByIdsException(List<Id> ids, ErrorSpecification errorSpecification) {
        String idsString;
        if (ids == null) {
            idsString = "null";
        } else {
            idsString = ids.stream()
                    .map(Id::id)
                    .toString();
        }
        return new NotFoundByIdException(idsString, errorSpecification);
    }
}

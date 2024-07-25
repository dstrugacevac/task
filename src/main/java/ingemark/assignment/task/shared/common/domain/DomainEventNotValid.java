package ingemark.assignment.task.shared.common.domain;


import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

public class DomainEventNotValid extends LogicalException {

    private DomainEventNotValid() {
        super(
                "Domain event is not valid",
                ErrorSpecification.DOMAIN_EVENT_NOT_VALID);
    }

    public static DomainEventNotValid domainEventNotValid() {
        return new DomainEventNotValid();
    }
}

package ingemark.assignment.task.shared.common.domain;

import ingemark.assignment.task.shared.common.infrastructure.error.exception.LogicalException;
import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;

public class DomainEventPublisherNotRegistered extends LogicalException {

    private DomainEventPublisherNotRegistered() {
        super(
                "DomainEventPublisher is not registered",
                ErrorSpecification.DOMAIN_EVENT_PUBLISHER_NOT_REGISTERED);
    }

    public static DomainEventPublisherNotRegistered domainEventPublisherNotRegistered() {
        return new DomainEventPublisherNotRegistered();
    }
}

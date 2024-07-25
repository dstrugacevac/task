package ingemark.assignment.task.shared.common.domain;

import ingemark.assignment.task.shared.common.domain.event.DomainEvent;

import java.util.List;

public interface DomainEventPublisher {

    default void publishEvent(DomainEvent event) {
        this.publishEvent((Object) event);
    }

    void publishEvent(Object event);


    void publishEvents(List<DomainEvent> event);
}

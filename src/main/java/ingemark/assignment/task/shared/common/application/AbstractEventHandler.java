package ingemark.assignment.task.shared.common.application;

import ingemark.assignment.task.shared.common.domain.event.DomainEvent;

public abstract class AbstractEventHandler<E extends DomainEvent> {

    public abstract void handle(E event);
}

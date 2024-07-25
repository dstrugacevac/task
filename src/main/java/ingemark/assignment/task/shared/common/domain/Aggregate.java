package ingemark.assignment.task.shared.common.domain;


import ingemark.assignment.task.shared.common.domain.event.DomainEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

public abstract class Aggregate<D extends AbstractAggregateRoot<D>> extends AbstractAggregateRoot<D> {


    @Override
    public <T> T registerEvent(T event) {
        if (!(event instanceof DomainEvent)) {
            throw DomainEventNotValid.domainEventNotValid();
        }

        return super.registerEvent(event);
    }
}

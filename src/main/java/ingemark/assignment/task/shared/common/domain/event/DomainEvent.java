package ingemark.assignment.task.shared.common.domain.event;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public abstract class DomainEvent extends ApplicationEvent {

    private final String traceId;

    public DomainEvent(Object source) {
        super(source);

        traceId = UUID.randomUUID().toString();
    }
}

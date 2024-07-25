package ingemark.assignment.task.product.domain.event;

import ingemark.assignment.task.shared.common.domain.event.DomainEvent;
import ingemark.assignment.task.shared.id.Id;

import java.util.UUID;

public class ProductCreatedEvent extends DomainEvent {

    private ProductCreatedEvent(UUID id) {
        super(id);
    }

    public static ProductCreatedEvent of(Id id) {
        return new ProductCreatedEvent(id.id());
    }

    public UUID getId() {
        return (UUID) source;
    }
}

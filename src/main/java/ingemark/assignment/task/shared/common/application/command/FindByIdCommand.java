package ingemark.assignment.task.shared.common.application.command;

import java.util.UUID;

public record FindByIdCommand(UUID id) {
    public static FindByIdCommand fromValue(UUID id) {
        return new FindByIdCommand(id);
    }
}

package ingemark.assignment.task.shared.common.application.command;

import org.springframework.data.domain.Pageable;

public record FetchCommand(
        Pageable pageable) {
}

package ingemark.assignment.task.shared.common.application.result;

import java.util.List;

public record FetchCommandResult<T>(
        List<T> elements,
        Long total) {

    public static <T> FetchCommandResult<T> of(List<T> elements, Long total) {
        return new FetchCommandResult<>(elements, total);
    }

}

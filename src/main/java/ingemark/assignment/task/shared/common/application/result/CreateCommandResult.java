package ingemark.assignment.task.shared.common.application.result;

public record CreateCommandResult<T>(T model) {

    public static <T> CreateCommandResult<T> of(T model) {
        return new CreateCommandResult<>(model);
    }
}

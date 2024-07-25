package ingemark.assignment.task.shared.common.application;

public abstract class AbstractCommandHandler<C, R> {

    public abstract R handle(C command);

}

package ingemark.assignment.task.shared.common.domain;

public interface Identifier<SELF, ID> extends ValueObject<SELF> {

    ID id();

}

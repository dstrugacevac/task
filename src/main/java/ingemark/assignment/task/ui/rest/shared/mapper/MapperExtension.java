package ingemark.assignment.task.ui.rest.shared.mapper;

import ingemark.assignment.task.shared.common.application.command.FetchCommand;
import ingemark.assignment.task.shared.common.application.command.FindByIdCommand;
import ingemark.assignment.task.shared.common.application.result.CreateCommandResult;
import ingemark.assignment.task.shared.common.application.result.FetchCommandResult;
import ingemark.assignment.task.shared.common.application.result.FindByIdCommandResult;
import ingemark.assignment.task.shared.common.domain.Identifier;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface MapperExtension {

    default UUID mapIdentifier(Identifier<?, UUID> identifier) {
        if (identifier == null) {
            return null;
        }

        return identifier.id();
    }

    default FindByIdCommand toCommand(UUID id) {
        return new FindByIdCommand(id);
    }

    default FetchCommand toCommand(Pageable pageable) {
        return new FetchCommand(pageable);
    }

    default <T> FetchCommandResult<T> toFetchResult(List<T> elements, Long total) {
        return new FetchCommandResult<T>(elements, total);
    }

    default <T> CreateCommandResult<T> toCreateResult(T model) {
        return new CreateCommandResult<>(model);
    }


    default <T> FindByIdCommandResult<T> toFinByIdResult(T model) {
        return new FindByIdCommandResult<>(model);
    }
}

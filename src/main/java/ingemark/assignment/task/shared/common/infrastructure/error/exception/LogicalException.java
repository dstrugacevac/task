package ingemark.assignment.task.shared.common.infrastructure.error.exception;

import ingemark.assignment.task.shared.common.infrastructure.error.specs.ErrorSpecification;
import org.springframework.http.HttpStatus;

public abstract class LogicalException extends RuntimeException {

    private final ErrorSpecification errorSpecification;

    protected LogicalException(String message, ErrorSpecification errorSpecification) {
        super(message);

        this.errorSpecification = errorSpecification;
    }


    protected LogicalException(String message, ErrorSpecification errorSpecification, Throwable cause) {
        super(message, cause);

        this.errorSpecification = errorSpecification;
    }

    public ErrorSpecification getErrorSpecification() {
        return errorSpecification;
    }

    public HttpStatus getHttpStatus() {
        return errorSpecification.getHttpStatus();
    }
}

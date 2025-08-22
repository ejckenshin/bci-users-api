package cl.smart.job.exercise.bci.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BciUserException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String keyMessage;
    private final Object[] params;

    public BciUserException(HttpStatus httpStatus, String keyMessage) {
        this.httpStatus = httpStatus;
        this.keyMessage = keyMessage;
        this.params = null;
    }

    public BciUserException(HttpStatus httpStatus, String keyMessage, Object[] params) {
        this.httpStatus = httpStatus;
        this.keyMessage = keyMessage;
        this.params = params;
    }
}

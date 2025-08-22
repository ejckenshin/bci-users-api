package cl.smart.job.exercise.bci.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ResponseExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        log.error("Error MethodArgumentNotValidException:", exception);
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(
                        fieldError -> errors.put(fieldError.getField(),
                                fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(errors.toString()).build());
    }

    @ExceptionHandler(BciUserException.class)
    public ResponseEntity<ErrorResponse> handlerBciUserException(BciUserException exception) {
        log.error("Error BciUserException", exception);
        String message;

        if (Objects.nonNull(exception.getParams())) {
            message = messageSource.getMessage(
                    exception.getKeyMessage(), exception.getParams(), null, LocaleContextHolder.getLocale());
        } else {
            message = messageSource.getMessage(
                    exception.getKeyMessage(), null, null, LocaleContextHolder.getLocale());
        }


        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorResponse.builder()
                        .message(message).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception exception) {
        log.error("Error Generic:", exception);
        String message = exception.getMessage();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .message(message).build());
    }
}

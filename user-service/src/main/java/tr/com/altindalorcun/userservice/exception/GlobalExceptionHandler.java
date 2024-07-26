package tr.com.altindalorcun.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handle(UserNotFoundException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

}

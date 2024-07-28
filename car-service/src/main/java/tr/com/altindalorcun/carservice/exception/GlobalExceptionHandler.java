package tr.com.altindalorcun.carservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handle(CarNotFoundException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ExistByLicensePlateException.class)
    public ResponseEntity<ExceptionResponse> handle(ExistByLicensePlateException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage()),
                HttpStatus.CONFLICT
        );
    }

}

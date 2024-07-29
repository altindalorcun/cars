package tr.com.altindalorcun.garage_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GarageAlreadyCreatedException.class)
    public ResponseEntity<ExceptionResponse> handle(GarageAlreadyCreatedException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(GarageNotFoundByOwnerIdException.class)
    public ResponseEntity<ExceptionResponse> handle(GarageNotFoundByOwnerIdException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

}

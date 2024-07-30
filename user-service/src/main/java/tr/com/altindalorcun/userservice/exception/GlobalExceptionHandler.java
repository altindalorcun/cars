package tr.com.altindalorcun.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tr.com.altindalorcun.userservice.client.exception.FeignExceptionMessage;
import tr.com.altindalorcun.userservice.client.exception.GarageAlreadyCreatedException;
import tr.com.altindalorcun.userservice.client.exception.GarageNotFoundByOwnerIdException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handle(UserNotFoundException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(GarageNotFoundByOwnerIdException.class)
    public ResponseEntity<FeignExceptionMessage> handle(GarageNotFoundByOwnerIdException exception) {
        return new ResponseEntity<>(
                exception.getExceptionMessage(),
                HttpStatus.resolve(exception.getExceptionMessage().status())
        );
    }

    @ExceptionHandler(GarageAlreadyCreatedException.class)
    public ResponseEntity<FeignExceptionMessage> handle(GarageAlreadyCreatedException exception) {
        return new ResponseEntity<>(
                exception.getExceptionMessage(),
                HttpStatus.resolve(exception.getExceptionMessage().status())
        );
    }

}

package tr.com.altindalorcun.userservice.client.exception;

import lombok.Getter;

public class GarageNotFoundByOwnerIdException extends RuntimeException {

    @Getter
    private final FeignExceptionMessage exceptionMessage;

    public GarageNotFoundByOwnerIdException(FeignExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

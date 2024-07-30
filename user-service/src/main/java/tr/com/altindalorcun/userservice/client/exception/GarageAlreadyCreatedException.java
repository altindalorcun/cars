package tr.com.altindalorcun.userservice.client.exception;

import lombok.Getter;

public class GarageAlreadyCreatedException extends RuntimeException {

    @Getter
    private final FeignExceptionMessage exceptionMessage;

    public GarageAlreadyCreatedException(FeignExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

package tr.com.altindalorcun.garage_service.client.exception;

import lombok.Getter;

public class CarNotFoundException extends RuntimeException {

    @Getter
    private final FeignExceptionMessage exceptionMessage;

    public CarNotFoundException(FeignExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

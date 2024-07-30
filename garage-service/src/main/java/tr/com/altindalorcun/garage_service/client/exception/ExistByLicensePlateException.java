package tr.com.altindalorcun.garage_service.client.exception;

import lombok.Getter;

public class ExistByLicensePlateException extends RuntimeException {

    @Getter
    private final FeignExceptionMessage exceptionMessage;

    public ExistByLicensePlateException(FeignExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

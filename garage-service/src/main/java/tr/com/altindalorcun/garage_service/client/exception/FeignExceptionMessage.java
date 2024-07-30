package tr.com.altindalorcun.garage_service.client.exception;

public record FeignExceptionMessage(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {
}

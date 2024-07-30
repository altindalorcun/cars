package tr.com.altindalorcun.userservice.client.exception;

public record FeignExceptionMessage(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {
}

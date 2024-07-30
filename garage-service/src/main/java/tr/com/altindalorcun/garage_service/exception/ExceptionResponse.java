package tr.com.altindalorcun.garage_service.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        LocalDateTime timestamp
) {
    public ExceptionResponse(String message) {
        this(message, LocalDateTime.now());
    }
}

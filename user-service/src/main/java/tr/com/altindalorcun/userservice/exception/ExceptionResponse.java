package tr.com.altindalorcun.userservice.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        LocalDateTime timestamp
) {
    public ExceptionResponse(String message) {
        this(message, LocalDateTime.now());
    }
}

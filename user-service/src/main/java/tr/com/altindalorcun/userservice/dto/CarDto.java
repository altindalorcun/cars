package tr.com.altindalorcun.userservice.dto;

import java.util.UUID;

public record CarDto(
        UUID id,
        String brand,
        String model,
        String licensePlate
) {
}

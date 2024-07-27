package tr.com.altindalorcun.garage_service.dto;

import java.util.UUID;

public record CarDto(
        UUID id,
        String brand,
        String model,
        String licensePlate
) {
}

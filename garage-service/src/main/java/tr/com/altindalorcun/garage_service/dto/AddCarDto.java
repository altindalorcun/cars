package tr.com.altindalorcun.garage_service.dto;

import java.util.UUID;

public record AddCarDto(
        UUID ownerId,
        String brand,
        String model,
        String licensePlate
) {
}

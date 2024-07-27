package tr.com.altindalorcun.garage_service.dto;

import java.util.List;
import java.util.UUID;

public record GarageDto(
        UUID id,
        UUID ownerId,
        List<CarDto> cars
) {
}

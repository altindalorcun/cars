package tr.com.altindalorcun.userservice.dto;

import java.util.List;
import java.util.UUID;

public record GarageDto(
        UUID id,
        UUID ownerId,
        List<CarDto> cars
) {
}

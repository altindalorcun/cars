package tr.com.altindalorcun.garage_service.dto;

import java.util.UUID;

public record AddGarageDto(
        UUID ownerId
) {
}

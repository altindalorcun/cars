package tr.com.altindalorcun.garage_service.dto;

public record CreateCarDto(
        String brand,
        String model,
        String licensePlate
) {
}

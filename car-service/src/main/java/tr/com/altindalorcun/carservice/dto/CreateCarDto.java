package tr.com.altindalorcun.carservice.dto;

public record CreateCarDto(
        String brand,
        String model,
        String licensePlate
) {
}

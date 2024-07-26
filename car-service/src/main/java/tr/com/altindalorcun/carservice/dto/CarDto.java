package tr.com.altindalorcun.carservice.dto;

import tr.com.altindalorcun.carservice.model.Car;

import java.util.UUID;

public record CarDto(
        UUID id,
        String brand,
        String model,
        String vrn
) {
    public CarDto(Car car) {
        this(car.getId(), car.getBrand(), car.getModel(), car.getVrn());
    }
}

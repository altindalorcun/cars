package tr.com.altindalorcun.garage_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tr.com.altindalorcun.garage_service.dto.CarDto;
import tr.com.altindalorcun.garage_service.dto.CreateCarDto;

import java.util.UUID;

@FeignClient(name = "car-service", path = "/api/car")
public interface CarServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<CarDto> findCarById(@PathVariable UUID id);

    @GetMapping("/license/{licensePlate}")
    ResponseEntity<CarDto> findCarByLicensePlate(@PathVariable String licensePlate);

    @PostMapping
    ResponseEntity<UUID> createCar(@RequestBody CreateCarDto dto);

}

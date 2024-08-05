package tr.com.altindalorcun.carservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.altindalorcun.carservice.dto.CarDto;
import tr.com.altindalorcun.carservice.dto.CreateCarDto;
import tr.com.altindalorcun.carservice.service.CarService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/car")
@Slf4j
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(service.gelAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable UUID id) {
        log.info("Car requested by id : " + id);
        return ResponseEntity.ok(service.findCarById(id));
    }

    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<CarDto> findCarByLicensePlate(@PathVariable String licensePlate) {
        log.info("Car requested by license plate");
        return ResponseEntity.ok(service.findCarByLicensePlate(licensePlate));
    }

    @PostMapping()
    public ResponseEntity<UUID> createCar(@RequestBody CreateCarDto dto) {
        log.info("Create car requested");
        return new ResponseEntity<>(service.createCar(dto), HttpStatus.CREATED);
    }
}

package tr.com.altindalorcun.carservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.altindalorcun.carservice.dto.CarDto;
import tr.com.altindalorcun.carservice.service.CarService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/car")
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
        return ResponseEntity.ok(service.findCarById(id));
    }

    @GetMapping("/vrn/{vrn}")
    public ResponseEntity<CarDto> findCarByVrn(@PathVariable String vrn) {
        return ResponseEntity.ok(service.findCarByVrn(vrn));
    }
}

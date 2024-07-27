package tr.com.altindalorcun.garage_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.altindalorcun.garage_service.dto.AddCarDto;
import tr.com.altindalorcun.garage_service.dto.AddGarageDto;
import tr.com.altindalorcun.garage_service.dto.GarageDto;
import tr.com.altindalorcun.garage_service.service.GarageService;

import java.util.UUID;

@RestController
@RequestMapping("/api/garage")
public class GarageController {

    private final GarageService service;

    public GarageController(GarageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UUID> createGarageWhenUserAdded(@RequestBody AddGarageDto dto) {
        return new ResponseEntity<>(service.createGarageWhenUserAdded(dto), HttpStatus.CREATED);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<GarageDto> findGarageByOwnerId(@PathVariable UUID ownerId) {
        return ResponseEntity.ok(service.findGarageByOwnerId(ownerId));
    }

    @PutMapping("/car")
    public ResponseEntity<GarageDto> addCarToGarage(@RequestBody AddCarDto dto) {
        return ResponseEntity.ok(service.addCarToGarage(dto));
    }
}

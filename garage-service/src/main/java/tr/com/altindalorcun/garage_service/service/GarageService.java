package tr.com.altindalorcun.garage_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.altindalorcun.garage_service.client.CarServiceClient;
import tr.com.altindalorcun.garage_service.dto.AddCarDto;
import tr.com.altindalorcun.garage_service.dto.AddGarageDto;
import tr.com.altindalorcun.garage_service.dto.CreateCarDto;
import tr.com.altindalorcun.garage_service.dto.GarageDto;
import tr.com.altindalorcun.garage_service.exception.GarageAlreadyCreatedException;
import tr.com.altindalorcun.garage_service.exception.GarageNotFoundByOwnerIdException;
import tr.com.altindalorcun.garage_service.model.Garage;
import tr.com.altindalorcun.garage_service.repository.GarageRepository;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GarageService {

    private final GarageRepository repository;
    private final CarServiceClient carServiceClient;

    public GarageService(GarageRepository repository, CarServiceClient carServiceClient) {
        this.repository = repository;
        this.carServiceClient = carServiceClient;
    }

    public UUID createGarageWhenUserAdded(AddGarageDto dto) {
        if (repository.existsByOwnerId(dto.ownerId())) {
            throw new GarageAlreadyCreatedException("User already has a garage : " + dto.ownerId());
        }
        return repository.save(new Garage(dto)).getId();
    }

    public GarageDto findGarageByOwnerId(UUID ownerId) {
        Garage garage = repository.findByOwnerId(ownerId)
                .orElseThrow(() -> new GarageNotFoundByOwnerIdException(ownerId));

        return new GarageDto(garage.getId(), garage.getOwnerId(),
                garage.getCarIds()
                        .stream()
                        .map(carServiceClient::findCarById)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList())
        );
    }

    public GarageDto addCarToGarage(AddCarDto dto) {
        Garage garage = repository.findByOwnerId(dto.ownerId())
                .orElseThrow(() -> new GarageNotFoundByOwnerIdException(dto.ownerId()));

        UUID createdCarId = carServiceClient
                .createCar(new CreateCarDto(dto.brand(), dto.model(), dto.licensePlate()))
                .getBody();

        garage.getCarIds().add(createdCarId);
        Garage updatedGarage = repository.save(garage);

        return new GarageDto(updatedGarage.getId(), updatedGarage.getOwnerId(),
                updatedGarage.getCarIds()
                        .stream()
                        .map(carServiceClient::findCarById)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList())
        );
    }
}

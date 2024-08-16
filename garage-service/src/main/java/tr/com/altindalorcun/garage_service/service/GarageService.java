package tr.com.altindalorcun.garage_service.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.altindalorcun.carservice.CarServiceGrpc;
import tr.com.altindalorcun.carservice.Id;
import tr.com.altindalorcun.garage_service.client.CarServiceClient;
import tr.com.altindalorcun.garage_service.dto.*;
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

    @GrpcClient("car-service")
    private CarServiceGrpc.CarServiceBlockingStub carServiceBlockingStub;

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
                        .map(id -> carServiceBlockingStub.findCarById(Id.newBuilder().setId(id.toString()).build()))
                        .map(carDto -> new CarDto(UUID.fromString(carDto.getId()), carDto.getBrand(), carDto.getModel(), carDto.getLicensePlate()))
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

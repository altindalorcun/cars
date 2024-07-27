package tr.com.altindalorcun.carservice.service;

import org.springframework.stereotype.Service;
import tr.com.altindalorcun.carservice.dto.CreateCarDto;
import tr.com.altindalorcun.carservice.dto.CarDto;
import tr.com.altindalorcun.carservice.exception.CarNotFoundException;
import tr.com.altindalorcun.carservice.exception.ExistByLicensePlateException;
import tr.com.altindalorcun.carservice.model.Car;
import tr.com.altindalorcun.carservice.repository.CarRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<CarDto> gelAllCars() {
        return repository.findAll().stream().map(CarDto::new).collect(Collectors.toList());
    }

    public CarDto findCarById(UUID id) {
        return repository.findById(id)
                .map(CarDto::new)
                .orElseThrow(() -> new CarNotFoundException("Car could not found by id : " + id));
    }

    public CarDto findCarByLicensePlate(String licensePlate) {
        return repository.findCarByLicensePlate(licensePlate)
                .map(CarDto::new)
                .orElseThrow(() -> new CarNotFoundException("Car could not found by licensePlate : " + licensePlate));
    }

    public UUID createCar(CreateCarDto dto) {
        if (repository.existsByLicensePlate(dto.licensePlate())) {
            throw new ExistByLicensePlateException();
        }

        return repository.save(new Car(dto)).getId();
    }
}

package tr.com.altindalorcun.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.altindalorcun.carservice.model.Car;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findCarByVrn(String vrn);

}

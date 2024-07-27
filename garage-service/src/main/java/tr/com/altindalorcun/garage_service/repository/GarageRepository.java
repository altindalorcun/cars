package tr.com.altindalorcun.garage_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.altindalorcun.garage_service.model.Garage;

import java.util.Optional;
import java.util.UUID;

public interface GarageRepository extends JpaRepository<Garage, UUID> {

    Boolean existsByOwnerId(UUID ownerId);
    Optional<Garage> findByOwnerId(UUID ownerId);
}

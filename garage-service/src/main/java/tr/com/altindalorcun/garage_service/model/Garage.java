package tr.com.altindalorcun.garage_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.altindalorcun.garage_service.dto.AddGarageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "garage")
@Getter
@Setter
@NoArgsConstructor
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "owner_id", nullable = false, unique = true)
    private UUID ownerId;

    @ElementCollection
    @Column(name = "car_ids")
    private List<UUID> carIds;

    public Garage(AddGarageDto dto) {
        setCarIds(new ArrayList<>());
        setOwnerId(dto.ownerId());
    }
}

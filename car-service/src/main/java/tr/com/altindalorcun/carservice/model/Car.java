package tr.com.altindalorcun.carservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.altindalorcun.carservice.dto.CreateCarDto;

import java.util.UUID;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    public Car(CreateCarDto dto) {
        setBrand(dto.brand());
        setModel(dto.model());
        setLicensePlate(dto.licensePlate());
    }
}

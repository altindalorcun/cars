package tr.com.altindalorcun.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.altindalorcun.userservice.dto.UserCreateDto;
import tr.com.altindalorcun.userservice.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String firstName;
    private String lastName;
    private String mail;
    // TODO : Use PrePersist!
    @ElementCollection
    private List<String> userCar = new ArrayList<>();

    public User(UserCreateDto dto) {
        this.username = dto.username();
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.mail = dto.mail();
    }

}

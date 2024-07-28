package tr.com.altindalorcun.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.altindalorcun.userservice.dto.UserCreateDto;

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
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "garage")
    private UUID garage;

    public User(UserCreateDto dto) {
        this.username = dto.username();
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.mail = dto.mail();
    }

}

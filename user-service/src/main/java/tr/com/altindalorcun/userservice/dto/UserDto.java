package tr.com.altindalorcun.userservice.dto;

import tr.com.altindalorcun.userservice.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        String firstName,
        String lastName,
        String mail,
        List<CarDto> userCars
) {
    public UserDto(User user) {
        this(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getMail(), new ArrayList<>());
    }
}

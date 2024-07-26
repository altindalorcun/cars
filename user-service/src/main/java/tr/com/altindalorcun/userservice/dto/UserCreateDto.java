package tr.com.altindalorcun.userservice.dto;

public record UserCreateDto(
        String username,
        String firstName,
        String lastName,
        String mail
) {

}

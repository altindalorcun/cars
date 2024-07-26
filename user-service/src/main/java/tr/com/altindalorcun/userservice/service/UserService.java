package tr.com.altindalorcun.userservice.service;

import org.springframework.stereotype.Service;
import tr.com.altindalorcun.userservice.dto.UserCreateDto;
import tr.com.altindalorcun.userservice.exception.UserNotFoundException;
import tr.com.altindalorcun.userservice.dto.UserDto;
import tr.com.altindalorcun.userservice.model.User;
import tr.com.altindalorcun.userservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public UserDto findById(UUID id) {
        return repository.findById(id)
                .map(UserDto::new)
                .orElseThrow(() -> new UserNotFoundException("User could not found by id : " + id));
    }

    public UserDto createUser(UserCreateDto dto) {
        User user = new User(dto);
        return new UserDto(repository.save(user));
    }
}


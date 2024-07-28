package tr.com.altindalorcun.userservice.service;

import org.springframework.stereotype.Service;
import tr.com.altindalorcun.userservice.client.GarageServiceClient;
import tr.com.altindalorcun.userservice.dto.AddGarageDto;
import tr.com.altindalorcun.userservice.dto.GarageDto;
import tr.com.altindalorcun.userservice.dto.UserCreateDto;
import tr.com.altindalorcun.userservice.dto.UserDto;
import tr.com.altindalorcun.userservice.exception.UserNotFoundException;
import tr.com.altindalorcun.userservice.model.User;
import tr.com.altindalorcun.userservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final GarageServiceClient garageServiceClient;

    public UserService(UserRepository repository, GarageServiceClient garageServiceClient) {
        this.repository = repository;
        this.garageServiceClient = garageServiceClient;
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(user -> new UserDto(user, garageServiceClient.findGarageByOwnerId(user.getId()).getBody()))
                .collect(Collectors.toList());
    }

    public UserDto findById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User could not found by id : " + id));
        GarageDto userGarage = garageServiceClient.findGarageByOwnerId(user.getId()).getBody();
        return new UserDto(user, userGarage);
    }

    public UUID createUser(UserCreateDto dto) {

        User user = new User(dto);
        User createdUser = repository.save(user);

        UUID createdGarageId = garageServiceClient
                .createGarageWhenUserAdded(new AddGarageDto(createdUser.getId()))
                .getBody();

        createdUser.setGarage(createdGarageId);
        repository.save(createdUser);


        return createdUser.getId();
    }
}


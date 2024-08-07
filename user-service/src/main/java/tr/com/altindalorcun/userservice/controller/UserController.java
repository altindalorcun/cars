package tr.com.altindalorcun.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.altindalorcun.userservice.dto.UserCreateDto;
import tr.com.altindalorcun.userservice.dto.UserDto;
import tr.com.altindalorcun.userservice.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService service;

    @Value("${service.count}")
    private Integer serviceCount;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Get all user requested");
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable UUID id) {
        log.info("User requested by id : " + id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody UserCreateDto dto) {
        log.info("Create user requested");
        return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getServiceCount() {
        return ResponseEntity.ok(serviceCount);
    }

}

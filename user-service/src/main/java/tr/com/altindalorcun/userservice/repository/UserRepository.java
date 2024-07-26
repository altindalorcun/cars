package tr.com.altindalorcun.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.altindalorcun.userservice.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

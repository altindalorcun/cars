package tr.com.altindalorcun.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tr.com.altindalorcun.userservice.dto.AddGarageDto;
import tr.com.altindalorcun.userservice.dto.GarageDto;

import java.util.UUID;

@FeignClient(name = "garage-service", path = "/api/garage")
public interface GarageServiceClient {

    @PostMapping
    ResponseEntity<UUID> createGarageWhenUserAdded(@RequestBody AddGarageDto dto);

    @GetMapping("/owner/{ownerId}")
    ResponseEntity<GarageDto> findGarageByOwnerId(@PathVariable UUID ownerId);

}

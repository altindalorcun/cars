package tr.com.altindalorcun.garage_service.exception;

import java.util.UUID;

public class GarageNotFoundByOwnerIdException extends RuntimeException{
    public GarageNotFoundByOwnerIdException(UUID ownerId) {
        super("Garage could not found by owner id : " + ownerId);
    }
}

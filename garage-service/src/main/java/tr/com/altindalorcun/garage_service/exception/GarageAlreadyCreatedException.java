package tr.com.altindalorcun.garage_service.exception;

public class GarageAlreadyCreatedException extends RuntimeException {
    public GarageAlreadyCreatedException(String message) {
        super(message);
    }
}

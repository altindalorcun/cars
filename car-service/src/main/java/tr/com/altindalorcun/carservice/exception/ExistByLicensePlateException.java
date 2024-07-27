package tr.com.altindalorcun.carservice.exception;

public class ExistByLicensePlateException extends RuntimeException {
    public ExistByLicensePlateException() {
        super("License Plate is already in use");
    }
}

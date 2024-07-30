package tr.com.altindalorcun.garage_service.client.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignExceptionMessage message = null;
        try {
            String responseBody = Util.toString(response.body().asReader(Charset.defaultCharset()));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);

            message = new FeignExceptionMessage(
                    jsonNode.path("timestamp").toString(),
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    jsonNode.path("message").toString(),
                    response.request().url());
        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }

        switch (response.status()) {
            case 404:
                throw new CarNotFoundException(message);
            case 409:
                throw new ExistByLicensePlateException(message);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}

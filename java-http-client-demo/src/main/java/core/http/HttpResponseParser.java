package core.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HttpResponseParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T parse(HttpResponse<String> response,
                       Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(response.body(), clazz);
    }

    public <T> T parse(HttpResponse<String> response,
                       TypeReference<T> typeRef) throws JsonProcessingException {
        return mapper.readValue(response.body(), typeRef);
    }

    public <T> Optional<T> tryParse(HttpResponse<String> response,
                                    Class<T> clazz) {
        try {
            return Optional.of(parse(response, clazz));
        } catch (IOException ex) {
            return Optional.empty();
        }
    }
}

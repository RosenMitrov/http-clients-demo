package core.http;

import java.net.http.HttpResponse;
import java.util.function.Supplier;

public class HttpErrorHandler {

    public void handleError(HttpResponse<?> response) {
        int statusCode = response.statusCode();

        if (statusCode >= 400) {
            throw new RuntimeException("Http error: " + statusCode + " - " + response.body());
        }
    }

    public <T> T handleOrFallback(HttpResponse<T> response,
                                  Supplier<T> fallback) {
        if (response.statusCode() >= 400) {
            return fallback.get();
        }

        return response.body();
    }
}

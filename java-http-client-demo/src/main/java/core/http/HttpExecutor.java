package core.http;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpExecutor {

    private final HttpClient httpClient;

    public HttpExecutor(Duration connectTimeout) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(
                        connectTimeout == null ?
                                Duration.ofSeconds(20) :
                                connectTimeout
                )
                .build();
    }

    public HttpResponse<String> execute(HttpRequest request) throws IOException, InterruptedException {
        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public CompletableFuture<HttpResponse<String>> executeAsync(HttpRequest request) {
        return this.httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}

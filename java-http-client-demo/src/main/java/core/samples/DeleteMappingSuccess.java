package core.samples;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteMappingSuccess {
    public static void main(String[] args) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:9999?id=111"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

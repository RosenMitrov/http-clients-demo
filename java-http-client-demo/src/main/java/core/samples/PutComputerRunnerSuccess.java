package core.samples;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.dtos.Computer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PutComputerRunnerSuccess {
    public static void main(String[] args) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            String id = "666";
            Computer computer = new Computer(id, "ASUS", "TUF GAMING A17", 2021, 2500.00);
            String jsonPayload = new ObjectMapper().writeValueAsString(computer);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:9999/api/computer/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .headers("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

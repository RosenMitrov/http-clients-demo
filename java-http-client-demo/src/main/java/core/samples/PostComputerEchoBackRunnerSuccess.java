package core.samples;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.dtos.Computer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostComputerEchoBackRunnerSuccess {
    public static void main(String[] args) {


        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            Computer createdComputer = new Computer("777", "HP", "EliteBook", 2024, 999.99);
            System.out.println("Created Computer " + createdComputer);
            String requestBody = new ObjectMapper().writeValueAsString(createdComputer);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:9999/api/computers/echo"))
                    .headers("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response body as JSON: " + response.body());
            System.out.println("STATUS CODE: " + response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

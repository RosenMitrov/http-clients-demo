package core.samples;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.dtos.Computer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostComputerRunnerSuccess {
    public static void main(String[] args) {


        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            Computer computer = new Computer("777", "HP", "EliteBook", 2024, 999.99);
            String requestBody = new ObjectMapper().writeValueAsString(computer);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:9999/api/computers"))
                    .headers("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(response.statusCode());
            Computer resultComputer = new ObjectMapper().readValue(response.body(), Computer.class);
            System.out.println(resultComputer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

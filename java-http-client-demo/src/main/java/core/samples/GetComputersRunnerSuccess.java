package core.samples;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.dtos.Computer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetComputersRunnerSuccess {

    public static void main(String[] args) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            System.out.println("Get success");
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:9999/api/computers"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(response.headers());
            System.out.println(response.statusCode());
            List<Computer> computers = new ObjectMapper().readValue(response.body(), new TypeReference<List<Computer>>() {
            });
            computers.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

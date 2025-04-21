package core.samples;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.StringJoiner;

public class GetComputerWithQueryParams {
    public static void main(String[] args) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            String id = "123";
            String brand = "Lenovo";
            String model = "ThinkPad";
            int year = 2025;
            double price = 2000.00;
            StringJoiner queryParams = getQueryParams(brand, model, year, price);
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(String.format("http://localhost:9999/api/computer/%s?%s", id, queryParams)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringJoiner getQueryParams(String brand,
                                               String model,
                                               int year,
                                               double price) {
        StringJoiner joiner = new StringJoiner("&");
        joiner.add("brand=" + brand);
        joiner.add("model=" + model);
        joiner.add("year=" + year);
        joiner.add("price=" + String.format("%.2f", price));
        return joiner;
    }
}

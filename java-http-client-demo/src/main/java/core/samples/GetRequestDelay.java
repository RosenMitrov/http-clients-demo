package core.samples;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GetRequestDelay {
    public static void main(String[] args) {
        try (HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))//This is the timeout for the TCP connection setup — the time it takes to establish a network connection with the server.
                .build()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .timeout(Duration.ofSeconds(6))//Whole delay TCP + The time to send the request, wait for the response, and receive the full response body.
                    .uri(URI.create("http://localhost:9999/api/delay"))
                    .headers("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            System.out.println("Expected connection timed out: " + e.getMessage());
        }
    }
}
/*
On Localhost/Development Environments: You can set the connectTimeout as low as 500 milliseconds to 2 seconds. Local connections are fast, so a lower value is sufficient.

On a Local Network (LAN): A typical range is 1 to 3 seconds. LAN connections should be relatively fast but still need a reasonable amount of time for network issues, such as packet loss or congestion.

Over the Internet/Remote Servers: Set the connectTimeout between 3 and 5 seconds, as establishing a connection can take longer with remote servers due to various factors like latency, DNS lookup, and network load.
 */
/*
For Fast APIs (Low Latency): For API calls that are expected to respond quickly (e.g., microservices or databases with low latency), 2 to 5 seconds is typically enough. The server should respond quickly to such requests, and anything longer could indicate an issue.

For Average APIs (Moderate Latency): For standard web APIs or services with moderate network latency, 5 to 15 seconds is usually a reasonable range. This accounts for the round-trip time and potential processing delays.

For Slow or High Latency APIs: If you're making requests to services that are known to be slower (e.g., cloud services, long-running jobs), you may need to allow 15 to 30 seconds or even longer. For example, an API that generates reports or performs complex calculations might need more time to respond.
 */
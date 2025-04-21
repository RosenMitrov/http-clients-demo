package core.http.apps;

import core.http.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GetWithTimeout {

    private static HttpErrorHandler errorHandler = new HttpErrorHandler();
    private static HttpResponseParser responseParser = new HttpResponseParser();

    public static void main(String[] args) throws IOException, InterruptedException {
        RequestContext context = RequestContext.builder()
                .withMethod("GET")
                .withBaseUrl("http://localhost:9999")
                .withPathUrl("/api/delay")
                .withRequestTimeout(Duration.ofSeconds(4))// throw timeout exception
//                .withRequestTimeout(Duration.ofSeconds(6)) // waits 5 seconds from the server
                .withHeaders("Content-Type", "application/json")
                .build();

        HttpRequest request = new HttpRequestBuilder(context).build();

        HttpExecutor httpExecutor = new HttpExecutor(context.getConnectTimeout());
        HttpResponse<String> response = httpExecutor.execute(request);
        errorHandler.handleError(response);
        Object result = responseParser.parse(response, Object.class);
        System.out.println(result);
    }
}

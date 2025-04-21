package core.http.apps;

import core.http.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostAuthSuccess {
    private static HttpResponseParser httpResponseParser = new HttpResponseParser();
    private static HttpErrorHandler errorHandler = new HttpErrorHandler();

    public static void main(String[] args) throws IOException, InterruptedException {
        RequestContext context = RequestContext.builder()
                .withBaseUrl("http://localhost:9999")
                .withPathUrl("/api/auth/login")
                .withMethod("POST")
                .withBody("{\"username\":\"John\", \"password\":\"secret123\"}")
                .build();
        HttpRequest request = new HttpRequestBuilder(context).build();
        HttpExecutor httpExecutor = new HttpExecutor(context.getConnectTimeout());
        HttpResponse<String> response = httpExecutor.execute(request);

        errorHandler.handleError(response);

        Object result = httpResponseParser.parse(response, Object.class);
        System.out.println(result);
    }
}

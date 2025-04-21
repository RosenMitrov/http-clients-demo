package core.http.apps;

import core.http.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OnlyWithBaseUrl {
    private static HttpErrorHandler httpErrorHandler = new HttpErrorHandler();
    private static HttpResponseParser httpResponseParser = new HttpResponseParser();

    public static void main(String[] args) throws IOException, InterruptedException {
        RequestContext context = RequestContext.builder()
                .withMethod("GET")
                .withBaseUrl("http://localhost:9999")
                .build();

        HttpRequest request = new HttpRequestBuilder(context).build();
        HttpExecutor httpExecutor = new HttpExecutor(context.getConnectTimeout());
        HttpResponse<String> response = httpExecutor.execute(request);

        httpErrorHandler.handleError(response);

        Object result = httpResponseParser.parse(response, Object.class);

        System.out.println(result);

    }
}

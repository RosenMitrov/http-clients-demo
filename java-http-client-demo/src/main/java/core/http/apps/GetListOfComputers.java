package core.http.apps;

import com.fasterxml.jackson.core.type.TypeReference;
import core.dtos.Computer;
import core.http.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetListOfComputers {
    private static HttpResponseParser httpResponseParser = new HttpResponseParser();
    private static HttpErrorHandler errorHandler = new HttpErrorHandler();

    public static void main(String[] args) throws IOException, InterruptedException {
        RequestContext context = RequestContext.builder()
                .withMethod("GET")
                .withBaseUrl("http://localhost:9999")
                .withPathUrl("/api/computers")
                .build();

        HttpRequest request = new HttpRequestBuilder(context).build();

        HttpExecutor httpExecutor = new HttpExecutor(context.getConnectTimeout());
        HttpResponse<String> response = httpExecutor.execute(request);
        errorHandler.handleError(response);
        List<Computer> computers = httpResponseParser.parse(response, new TypeReference<List<Computer>>() {
        });

        computers.forEach(System.out::println);

    }
}

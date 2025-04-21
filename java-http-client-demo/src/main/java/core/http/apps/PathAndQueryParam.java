package core.http.apps;

import core.dtos.Computer;
import core.http.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PathAndQueryParam {
    private static HttpErrorHandler httpErrorHandler = new HttpErrorHandler();
    private static HttpResponseParser httpResponseParser = new HttpResponseParser();

    public static void main(String[] args) throws IOException, InterruptedException {
        String id = "123";
        String brand = "Lenovo";
        String model = "ThinkPad";
        int year = 2025;
        double price = 2000.00;
        RequestContext context = RequestContext.builder()
                .withMethod("GET")
//                .withRequestTimeout(Duration.ofSeconds(10))
//                .withConnectTimeout(Duration.ofSeconds(5))
                .withBaseUrl("http://localhost:9999")
                .withPathUrl("/api/computer/{ID}")
                .withPathParams("ID", id)
                .withQueryParams("brand", brand)
                .withQueryParams("model", model)
                .withQueryParams("year", String.valueOf(year))
                .withQueryParams("price", String.format("%.2f", price))
                .build();

        HttpRequest request = new HttpRequestBuilder(context).build();

        HttpExecutor httpExecutor = new HttpExecutor(context.getConnectTimeout());
        HttpResponse<String> response = httpExecutor.execute(request);

        httpErrorHandler.handleError(response);
//        httpErrorHandler.handleOrFallback(response,() -> {});
        Computer parse = httpResponseParser.parse(response, Computer.class);
        System.out.println(parse);
    }
}

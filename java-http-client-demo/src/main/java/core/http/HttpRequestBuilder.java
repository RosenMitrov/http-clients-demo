package core.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class HttpRequestBuilder {

    private final RequestContext context;

    public HttpRequestBuilder(RequestContext context) {
        this.context = context;
    }

    public HttpRequest build() {
        URI uri = buildUri();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(
                        context.getRequestTimeout() == null ?
                                Duration.ofSeconds(30) :
                                context.getRequestTimeout()
                );

        context.getHeaders().forEach(requestBuilder::headers);

        switch (Objects.requireNonNull(context.getMethod(), "Http method cannot be null or empty")) {
            case "GET" -> requestBuilder.GET();
            case "POST" -> requestBuilder.POST(HttpRequest.BodyPublishers.ofString(context.getBody()));
            case "PUT" -> requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(context.getBody()));
            case "DELETE" -> requestBuilder.DELETE();
            default -> throw new UnsupportedOperationException("Unsupported method " + context.getMethod());
        }

        return requestBuilder.build();
    }

    private URI buildUri() {
        String endpoint = context.getPathUrl();
        for (var entry : context.getPathParams().entrySet()) {
            endpoint = endpoint.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        StringJoiner queryParamsResult = new StringJoiner("&");
        if (!context.getQueryParams().isEmpty()) {
            queryParamsResult.add("?");
            context.getQueryParams()
                    .forEach((k, v) -> queryParamsResult.add(k + "=" + v));
        }

        return URI.create(String.format("%s%s%s",
                Objects.requireNonNull(context.getBaseUrl(), "Base Url cannot be null or empty"),
                Optional.ofNullable(endpoint).orElse(""),
                queryParamsResult));
    }
}

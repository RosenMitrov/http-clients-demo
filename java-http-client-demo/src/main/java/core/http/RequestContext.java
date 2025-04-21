package core.http;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private final String baseUrl;
    private final String pathUrl;
    private final String method;
    private final String body;
    private final Map<String, String> pathParams;
    private final Map<String, String> queryParams;
    private final Map<String, String> headers;
    private final Duration connectTimeout;
    private final Duration requestTimeout;

    public RequestContext(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.pathUrl = builder.pathUrl;
        this.method = builder.method;
        this.body = builder.body == null ? "" : builder.body;
        this.pathParams = Map.copyOf(builder.pathParams);
        this.queryParams = Map.copyOf(builder.queryParams);
        this.headers = Map.copyOf(builder.headers);
        this.connectTimeout = builder.connectTimeout;
        this.requestTimeout = builder.requestTimeout;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getRequestTimeout() {
        return requestTimeout;
    }

    public static Builder builder() {
        return new Builder().init();
    }

    public static final class Builder {
        private String baseUrl;
        private String pathUrl;
        private String method;
        private String body;
        private Map<String, String> pathParams;
        private Map<String, String> queryParams;
        private Map<String, String> headers;
        private Duration connectTimeout;
        private Duration requestTimeout;

        public Builder withBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder withPathUrl(String pathUrl) {
            this.pathUrl = pathUrl;
            return this;
        }

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Builder withPathParams(String key,
                                      String value) {
            this.pathParams.put(key, value);
            return this;
        }

        public Builder withQueryParams(String key,
                                       String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public Builder withHeaders(String key,
                                   String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder withConnectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder withRequestTimeout(Duration requestTimeout) {
            this.requestTimeout = requestTimeout;
            return this;
        }

        public RequestContext build() {
            return new RequestContext(this);
        }

        private Builder init() {
            this.pathParams = new HashMap<>();
            this.queryParams = new HashMap<>();
            this.headers = new HashMap<>();
            return this;
        }
    }
}

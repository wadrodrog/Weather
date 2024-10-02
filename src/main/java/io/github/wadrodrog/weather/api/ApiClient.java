package io.github.wadrodrog.weather.api;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * API client. This is a wrapper of HttpClient.
 */
public class ApiClient {
    public final String baseURL;
    public final HttpClient client;
    private String endpoint;
    private final HashMap<String, String> params = new HashMap<>();

    /**
     * Initializes an API client.
     *
     * @param  baseURL  base URL
     */
    public ApiClient(String baseURL) {
        this.baseURL = baseURL;
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Makes an HTTP request and clears endpoint, params.
     *
     * @return  JSON response
     */
    public JSONObject makeRequest() throws IOException, InterruptedException {
        // Get current URI and then clear endpoint and params.
        URI uri = getURI();
        endpoint = "";
        params.clear();

        // Make request
        HttpRequest request = HttpRequest.newBuilder(uri).header("accept", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    /**
     * Sets an endpoint for URI.
     *
     * @param  value  endpoint starting with '/'
     */
    public void setEndpoint(String value) throws InvalidParameterException {
        if (!value.startsWith("/")) {
            throw new InvalidParameterException("Endpoint must start with '/'.");
        }
        this.endpoint = value;
    }

    /**
     * Adds a parameter to URI.
     *
     * @param  key  parameter key
     * @param  value  parameter value
     */
    public void addParam(String key, String value) {
        params.put(key, value);
    }

    /**
     * Constructs a URI string from baseURL, endpoint and params and returns.
     *
     * @return  URI string
     */
    private URI getURI() {
        return URI.create(baseURL + endpoint + getParams());
    }

    /**
     * Constructs a parameters string.
     *
     * @return  parameters string
     */
    private String getParams() {
        StringBuilder result = new StringBuilder();

        for (String param : params.keySet()) {
            result.append("&").append(param).append("=").append(params.get(param));
        }

        if (!result.isEmpty()) {
            result.replace(0, 1, "?");
        }

        return result.toString();
    }
}

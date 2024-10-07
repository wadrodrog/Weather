package io.github.wadrodrog.weather.api;

import io.github.wadrodrog.weather.types.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class ApiClientTest {
    final String BASE_URL = "https://example.com/api/v1";
    final String ENDPOINT = "/search";
    ApiClient client;

    @BeforeEach
    public void init() {
        client = new ApiClient(BASE_URL);
    }

    @Test
    @DisplayName("Provide invalid endpoint not starting with '/'")
    public void invalidEndpoint() {
        assertThrows(InvalidParameterException.class, () -> client.setEndpoint("search"));
    }

    @Test
    @DisplayName("Make request")
    public void shouldMakeRequest() throws IOException, InterruptedException {
        // Just endpoint, no parameters
        client.setEndpoint(ENDPOINT);
        Response response1 = client.makeRequest();
        assertEquals(BASE_URL + ENDPOINT, response1.uri.toString());

        // After making request, endpoint should be cleared
        Response response2 = client.makeRequest();
        assertEquals(BASE_URL, response2.uri.toString());

        // Endpoint and parameters
        // NOTE: It adds parameters in random order
        client.setEndpoint(ENDPOINT);
        client.addParam("query", "test");
        client.addParam("sort", "size");
        Response response3 = client.makeRequest();
        assertEquals(BASE_URL + ENDPOINT + "?query=test&sort=size", response3.uri.toString());

        // Cleanup check
        Response response4 = client.makeRequest();
        assertEquals(BASE_URL, response4.uri.toString());
    }
}
package io.github.wadrodrog.weather.api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * api.Geocoding API implementation.
 * <a href="https://open-meteo.com/en/docs/geocoding-api">Documentation</a>
 */
public class Geocoding extends ApiClient {
    static final String BASE_URL = "https://geocoding-api.open-meteo.com/v1";

    public Geocoding() {
        super(BASE_URL);
    }

    /**
     * Returns search results for a city name.
     *
     * @param  query  city name
     * @return search results
     */
    public JSONArray search(String query) throws IOException, InterruptedException {
        // Initialize URI
        setEndpoint("/search");
        addParam("name", query);

        // Make request
        JSONObject jsonObject = makeRequest().getJSON();

        if (jsonObject.has("results")) {
            return jsonObject.getJSONArray("results");
        }
        return new JSONArray(); // Not found
    }
}

package io.github.wadrodrog.weather.api;

import org.json.JSONObject;
import io.github.wadrodrog.weather.types.City;

import java.io.IOException;

/**
 * api.OpenMeteo API implementation.
 * <a href="https://open-meteo.com/en/docs">Documentation</a>
 */
public class OpenMeteo extends ApiClient {
    static final String BASE_URL = "https://api.open-meteo.com/v1";

    public OpenMeteo() {
        super(BASE_URL);
    }

    public JSONObject forecast(City city) throws IOException, InterruptedException {
        setEndpoint("/forecast");
        addParam("latitude", String.valueOf(city.latitude));
        addParam("longitude", String.valueOf(city.longitude));
        addParam("hourly", "temperature_2m");
        addParam("forecast_days", "3");

        return makeRequest().getJSON();
    }
}

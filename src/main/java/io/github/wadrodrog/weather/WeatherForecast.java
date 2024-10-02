package io.github.wadrodrog.weather;

import io.github.wadrodrog.weather.api.OpenMeteo;
import org.json.JSONArray;
import org.json.JSONObject;
import io.github.wadrodrog.weather.types.City;
import io.github.wadrodrog.weather.types.Hour;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * High-level class for getting weather forecast.
 */
public class WeatherForecast {
    private final OpenMeteo API;

    public WeatherForecast() {
        API = new OpenMeteo();
    }

    public String hourly(City city) throws IOException, InterruptedException {
        JSONObject response = API.forecast(city);
        ArrayList<Hour> hours = interpretHours(response, city.timezone);
        return hoursString(hours, city.timezone);
    }

    private ArrayList<Hour> interpretHours(JSONObject response, ZoneId cityTimezone) {
        ZoneId timezone = ZoneId.of(response.getString("timezone"));
        String temperatureUnits = response.getJSONObject("hourly_units").getString("temperature_2m");

        JSONObject hourly = response.getJSONObject("hourly");
        JSONArray time = hourly.getJSONArray("time");
        JSONArray temperature = hourly.getJSONArray("temperature_2m");

        ArrayList<Hour> hours = new ArrayList<>();

        for (int i = 0; i < time.length(); i++) {
            // Use current city's timezone
            LocalDateTime localDateTime = LocalDateTime.parse(time.getString(i));
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, timezone);
            ZonedDateTime timestamp = zonedDateTime.withZoneSameInstant(cityTimezone);

            hours.add(new Hour(timestamp, temperature.getDouble(i), temperatureUnits));
        }

        return hours;
    }

    private String hoursString(ArrayList<Hour> hours, ZoneId cityTimezone) {
        StringBuilder result = new StringBuilder();

        for (Hour hour : hours) {
            if (hour.isInFuture(cityTimezone)) {
                result.append(hour).append('\n');
            }
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}

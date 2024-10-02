package io.github.wadrodrog.weather.types;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents weather forecast for a single hour.
 */
public class Hour {
    ZonedDateTime timestamp;
    Temperature temperature;

    private final String TIMESTAMP_FORMAT = "dd.MM HH:mm";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);

    public Hour(ZonedDateTime timestamp, double temperatureValue, String temperatureUnit) {
        this.timestamp = timestamp;
        this.temperature = new Temperature(temperatureValue, temperatureUnit);
    }

    public boolean isInFuture(ZoneId timezone) {
        ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(timezone).minusHours(1);
        return timestamp.isAfter(now);
    }

    public String toString() {
        return timestamp.format(FORMATTER) + ": " + temperature;
    }
}

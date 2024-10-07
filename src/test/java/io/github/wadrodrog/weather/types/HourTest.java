package io.github.wadrodrog.weather.types;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HourTest {
    @Test
    public void isInFuture() {
        ZoneId tz = ZoneId.of("UTC");
        ZonedDateTime now = ZonedDateTime.now(tz);
        Hour hour1 = new Hour(now, 10, "°C");
        assertTrue(hour1.isInFuture(tz));
        Hour hour2 = new Hour(now.minusHours(1), 10, "°C");
        assertFalse(hour2.isInFuture(tz));
    }
}
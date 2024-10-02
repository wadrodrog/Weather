package io.github.wadrodrog.weather.types;

/**
 * Represents temperature.
 */
public class Temperature {
    double value;
    String unit;

    public Temperature(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public String toString() {
        return Math.round(value) + " " + unit;
    }
}

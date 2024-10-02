package io.github.wadrodrog.weather.types;

import org.json.JSONObject;

import java.time.ZoneId;

/**
 * types.City class.
 */
public class City {
    public final double latitude;
    public final double longitude;
    public final ZoneId timezone;

    int id;
    String name;
    String country;
    String admin1; // First administrative level

    public City(JSONObject jsonObject) {
        this.id = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
        this.country = jsonObject.getString("country");
        this.latitude = jsonObject.getDouble("latitude");
        this.longitude = jsonObject.getDouble("longitude");
        this.timezone = ZoneId.of(jsonObject.getString("timezone"));

        if (jsonObject.has("admin1")) {
            this.admin1 = jsonObject.getString("admin1");
        }
    }

    public String toString() {
        if (admin1 == null) {
            return name + ", " + country;
        }
        return name + ", " + admin1 + ", " + country;
    }
}

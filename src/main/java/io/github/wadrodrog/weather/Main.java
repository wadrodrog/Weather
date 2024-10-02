package io.github.wadrodrog.weather;

import io.github.wadrodrog.weather.types.City;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Search search = new Search();

        City city = search.interactiveGetCity();

        System.out.println("Getting weather for " + city + "...");

        WeatherForecast weatherForecast = new WeatherForecast();
        System.out.println(weatherForecast.hourly(city));
        System.out.println("Weather data by Open-Meteo.com (CC BY 4.0)");
    }
}
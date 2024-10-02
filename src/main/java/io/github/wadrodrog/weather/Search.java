package io.github.wadrodrog.weather;

import io.github.wadrodrog.weather.api.Geocoding;
import org.json.JSONArray;
import io.github.wadrodrog.weather.types.City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interactive search via console.
 */
public class Search {
    private final Scanner scanner;

    public Search() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs an interactive process in which user searches for the desired city.
     * <p>
     * Returns a types.City object that can then be used to get the weather.
     *
     * @return      the chosen city
     * @see         City
     */
    public City interactiveGetCity() throws IOException, InterruptedException {
        Geocoding geocoding = new Geocoding();
        JSONArray jsonArray = new JSONArray();

        while (jsonArray.isEmpty()) {
            String query = inputQuery("city name");
            jsonArray = geocoding.search(query);
            if (jsonArray.isEmpty()) {
                System.out.println("No cities found.");
            }
        }

        ArrayList<City> cities = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            City city = new City(jsonArray.getJSONObject(i));
            cities.add(city);
            System.out.println((i + 1) + ". " + city);
        }

        int choice = inputChoice("city", cities.size());

        return cities.get(choice - 1);
    }

    private String inputQuery(String term) {
        System.out.print("Enter " + term + ": ");
        return scanner.nextLine();
    }

    private int inputChoice(String term, int max) {
        while (true) {
            System.out.print("Choose " + term + ": ");
            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Don't get in the infinity loop
                System.out.println("Invalid number.");
                continue;
            }

            if (choice > max || choice < 1) {
                System.out.println("Invalid choice.");
                continue;
            }

            return choice;
        }
    }
}

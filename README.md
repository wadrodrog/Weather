# Weather Java API

Simple Java program that gets weather forecast via Open-Meteo API.

This is a learning project.

## Features

- Search for city via interactive text interface
- Get hourly weather forecast

## Tech stack

- Nested classes
- Packages
- JSON parsing org.json
- HTTP client
- Maven project

## Build

Install [Git](https://git-scm.com), OpenJDK 22 and [Maven](https://maven.apache.org).

```shell
git clone https://github.com/wadrodrog/Weather.git
cd Weather
mvn package
java -jar target/Weather-1.0-SNAPSHOT.jar
```

## License

This project is licensed under MIT License. See [LICENSE](/LICENSE) file.

Weather data by [Open-Meteo.com](https://open-meteo.com) ([CC BY 4.0](https://creativecommons.org/licenses/by/4.0)).

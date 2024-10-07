package io.github.wadrodrog.weather.types;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpResponse;

public class Response {
    public final int code;
    public final URI uri;
    private final String body;

    public Response(HttpResponse<String> response) {
        code = response.statusCode();
        body = response.body();
        uri = response.uri();
    }

    public JSONObject getJSON() {
        return new JSONObject(body);
    }
}

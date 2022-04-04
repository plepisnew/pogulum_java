package com.example.pogulum.util;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class Http {

    private final HttpClient client = HttpClient.newHttpClient();

    private static Http instance;

    public static Http getInstance(){
        instance = instance == null ? new Http() : instance;
        return instance;
    }

    private Http(){
    }

    public String BASE_URL = "http://localhost:8080/";

    public HttpResponse<String> get(String endpoint) throws IOException, InterruptedException {

        final HttpRequest req = HttpRequest
                .newBuilder(URI.create(BASE_URL + endpoint))
                .GET()
                .build();

        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }
}

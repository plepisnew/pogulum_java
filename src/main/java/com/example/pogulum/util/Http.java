package com.example.pogulum.util;
import com.fasterxml.jackson.core.JsonProcessingException;

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

    private final String TOKEN = "";
    private final String CLIENT_ID = "";

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

    public HttpResponse<String> authGet(String endpoint) throws IOException, InterruptedException {
        final HttpRequest req = HttpRequest
                .newBuilder(URI.create(endpoint))
                .GET()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Client-Id", CLIENT_ID)
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String endpoint, Object value) throws IOException, InterruptedException {
        final HttpRequest req = HttpRequest
                .newBuilder(URI.create(endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(Json.getInstance().stringify(value)))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }
}

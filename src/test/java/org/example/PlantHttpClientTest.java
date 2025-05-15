package org.example;

import org.junit.jupiter.api.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class PlantHttpClientTest {

    private static final String BASE_URL = "http://localhost:8000/plant?name=";

    @Test
    public void testHttpClientWithKnownPlant() throws Exception {
        String knownPlant = "mint";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + knownPlant))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().toLowerCase().contains("mint"));
    }

    @Test
    public void testHttpClientWithUnknownPlant() throws Exception {
        String unknownPlant = "ThisPlantDoesNotExistXYZ";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + unknownPlant))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertEquals("Plant not found.", response.body());
    }
}

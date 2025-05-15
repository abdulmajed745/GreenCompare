package org.example;

import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlantHttpServerTest {

    private Thread serverThread;

    @BeforeAll
    public void startServer() throws Exception {
        serverThread = new Thread(() -> {
            try {
                PlantHttpServer.main(new String[]{});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();
        Thread.sleep(1000);
    }

    @AfterAll
    public void stopServer() throws InterruptedException {
        serverThread.interrupt();
        serverThread.join(1000);
    }

    @Test
    public void testHttpServerReturnsKnownPlant() throws Exception {
        String plantName = "mint";
        URL url = new URL("http://localhost:8000/plant?name=" + plantName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        assertEquals(200, responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line).append("\n");
        }
        in.close();

        String responseText = response.toString().trim();
        assertFalse(responseText.isEmpty());
        assertTrue(responseText.toLowerCase().contains("mint"));
    }

    @Test
    public void testHttpServerReturnsNotFoundForUnknownPlant() throws Exception {
        String plantName = "ThisPlantDoesNotExist12345";
        URL url = new URL("http://localhost:8000/plant?name=" + plantName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        assertEquals(200, responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line).append("\n");
        }
        in.close();

        String responseText = response.toString().trim();
        assertEquals("Plant not found.", responseText);
    }
}

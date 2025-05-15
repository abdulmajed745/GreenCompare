package org.example;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlantServerTest {

    private static final int PORT = 12345;
    private Thread serverThread;

    @BeforeAll
    public void startServer() throws InterruptedException {
        serverThread = new Thread(() -> PlantServer.main(new String[]{}));
        serverThread.start();
        Thread.sleep(1000); // Wait for server to start
    }

    @AfterAll
    public void stopServer() throws InterruptedException {
        serverThread.interrupt();
        serverThread.join(1000);
    }

    @Test
    public void testServerRespondsToKnownPlant() throws IOException {
        try (Socket socket = new Socket("localhost", PORT);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write("mint\n");
            out.flush();

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }

            String responseStr = response.toString().trim();
            System.out.println("Response from server:\n" + responseStr);

            assertFalse(responseStr.isEmpty(), "Response should not be empty");
            assertTrue(responseStr.toLowerCase().contains("mint"), "Response should contain 'mint'");
        }
    }

    @Test
    public void testServerRespondsToUnknownPlant() throws IOException {
        try (Socket socket = new Socket("localhost", PORT);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write("ThisPlantDoesNotExist12345\n");
            out.flush();

            String response = in.readLine();
            assertEquals("Plant not found.", response);
        }
    }
}

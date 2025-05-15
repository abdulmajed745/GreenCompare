package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.Socket;
import static org.junit.jupiter.api.Assertions.*;

public class PlantClientTest {

    @Test
    public void testClientGetsAnyResponse() throws IOException {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write("anything\n");
            out.flush();

            String response = in.readLine();
            System.out.println("Response: " + response);

            assertNotNull(response, "Response should not be null");
        }
    }
}

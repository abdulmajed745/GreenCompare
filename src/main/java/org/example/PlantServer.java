package org.example;

import java.io.*;
import java.net.*;
import java.sql.*;

public class PlantServer {
    public static void main(String[] args) {
        int port = 12345;
        PlantDatabase db = new PlantDatabase();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Plant Server is running on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket, db)).start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket, PlantDatabase db) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String plantName = in.readLine();
            Plant plant = db.findPlant(plantName);

            if (plant != null) {
                out.println(plant.toString());
            } else {
                out.println("Plant not found.");
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

}


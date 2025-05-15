package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;

public class PlantHttpServer {
    private static PlantDatabase db = new PlantDatabase();

    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/plant", new PlantHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("HTTP Server started at http://localhost:" + port);
    }

    static class PlantHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery(); // e.g. name=rose
            String name = "";

            if (query != null && query.startsWith("name=")) {
                name = query.substring(5);
            }

            Plant plant = db.findPlant(name);
            String response;

            if (plant != null) {
                response = plant.toString();
            } else {
                response = "Plant not found.";
            }

            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}

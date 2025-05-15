package org.example;

import java.sql.*;

public class PlantDatabase {
    private Connection connection;

    public PlantDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/plant_database";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public synchronized Plant findPlant(String name) {
        try {
            String query = "SELECT * FROM plants WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String[] data = {
                        rs.getString("name"),
                        rs.getString("light"),
                        rs.getString("watering"),
                        rs.getString("humidity"),
                        rs.getString("temperature"),
                        rs.getString("fertilizer"),
                        rs.getString("pruning"),
                        rs.getString("propagation"),
                        rs.getString("notes")
                };
                return new Plant(data);
            }
        } catch (SQLException e) {
            System.out.println("\nyour plant does not exist dude !!");
        }
        return null;
    }

    public synchronized void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

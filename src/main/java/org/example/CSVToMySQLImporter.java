package org.example;

import java.io.*;
import java.sql.*;

public class CSVToMySQLImporter {
    public static void main(String[] args) {
        String csvFile = "/C:/Users/abdul/OneDrive/Desktop/GreenCompare/plant_dataset.csv/";
        String dbUrl = "jdbc:mysql://localhost:3306/plant_database";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {


            verifyTableStructure(conn);


            String sql = "INSERT INTO plants (name, light, watering, humidity, temperature, " +
                    "fertilizer, pruning, propagation, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);


            br.readLine();


            String line;
            int count = 0;
            int errorCount = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                try {

                    for (int i = 0; i < 9; i++) {
                        String value = (i < data.length) ? cleanValue(data[i]) : "";
                        stmt.setString(i+1, value);
                    }

                    stmt.executeUpdate();
                    count++;

                    // Progress reporting
                    if (count % 100 == 0) {
                        System.out.println("Imported " + count + " records...");
                    }
                } catch (SQLException e) {
                    errorCount++;
                    System.err.println("Error in row " + (count + errorCount) + ": " + e.getMessage());
                }
            }

            System.out.println("Import completed. Success: " + count + ", Errors: " + errorCount);

        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void verifyTableStructure(Connection conn) throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS plants (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "light VARCHAR(255), " +
                "watering VARCHAR(255), " +
                "humidity VARCHAR(255), " +
                "temperature VARCHAR(255), " +
                "fertilizer VARCHAR(255), " +
                "pruning VARCHAR(255), " +
                "propagation VARCHAR(255), " +
                "notes TEXT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    protected static String cleanValue(String value) {
        return value.trim()
                .replaceAll("^\"+|\"+$", "")
                .replaceAll("\"{2}", "\"")
                .replace("\\\"", "\"")
                .replace("\\'", "'");
    }

}
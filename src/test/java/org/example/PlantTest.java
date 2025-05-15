package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlantTest {

    @Test
    public void testPlantConstructorAndGetters() {
        String[] data = {
                "Rose", "Full Sun", "Moderate", "Medium", "20-25C",
                "Monthly", "Regular", "Cuttings", "Beautiful flower"
        };

        Plant plant = new Plant(data);

        assertEquals("Rose", plant.getName());
        assertEquals("Full Sun", plant.getLight());
        assertEquals("Moderate", plant.getWatering());
        assertEquals("Medium", plant.getHumidity());
        assertEquals("20-25C", plant.getTemperature());
        assertEquals("Monthly", plant.getFertilizer());
        assertEquals("Regular", plant.getPruning());
        assertEquals("Cuttings", plant.getPropagation());
        assertEquals("Beautiful flower", plant.getNotes());
    }
}

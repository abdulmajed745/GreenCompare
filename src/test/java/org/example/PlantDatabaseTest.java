package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlantDatabaseTest {

    private PlantDatabase plantDatabase;

    @BeforeEach
    public void setUp() {
        plantDatabase = new PlantDatabase();
    }

    @Test
    public void testFindPlantExists() {
        // Replace "Rose" with a plant name you know exists in your DB
        Plant plant = plantDatabase.findPlant("Rose");
        assertNotNull(plant, "Plant should be found in the database");
        assertEquals("Rose", plant.getName(), "Plant name should match the query");
    }

    @Test
    public void testFindPlantDoesNotExist() {
        Plant plant = plantDatabase.findPlant("NoSuchPlant");
        assertNull(plant, "Plant should be null if not found");
    }

    @AfterEach
    public void tearDown() {
        plantDatabase.close();
    }
}

package org.example;

public class Plant {
    String name, light, watering, humidity, temperature, fertilizer, pruning, propagation, notes;

    public Plant(String[] data) {
        name = data[0];
        light = data[1];
        watering = data[2];
        humidity = data[3];
        temperature = data[4];
        fertilizer = data[5];
        pruning = data[6];
        propagation = data[7];
        notes = data[8];
    }

    @Override
    public String toString() {
        return "\n " + name + " Care Guide \n"
                + "Light: " + light + "\nWatering: " + watering + "\nHumidity: " + humidity
                + "\nTemperature: " + temperature + "\nFertilizer: " + fertilizer + "\nPruning: "
                + pruning + "\nPropagation: " + propagation + "\nNotes: " + notes + "\n";
    }

    public String getName() { return name; }
    public String getLight() { return light; }
    public String getWatering() { return watering; }
    public String getHumidity() { return humidity; }
    public String getTemperature() { return temperature; }
    public String getFertilizer() { return fertilizer; }
    public String getPruning() { return pruning; }
    public String getPropagation() { return propagation; }
    public String getNotes() { return notes; }
}

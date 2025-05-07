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
}
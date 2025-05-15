package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PlantDatabase db = new PlantDatabase();


        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n GreenCompare ");
                System.out.println("1.  Search for a single plant");
                System.out.println("2.  Compare two plants");
                System.out.println("3.  Exit");
                System.out.print(" \nEnter your choice: ");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {

                    System.out.print(" Enter the plant name: ");
                    String name = scanner.nextLine();
                    Thread singleSearch = new Thread(() -> {
                        Plant plant = db.findPlant(name);
                        if (plant != null) {
                            System.out.println(plant);
                        } else {
                            System.out.println("try again ");
                        }
                    });
                    singleSearch.start();
                }

                else if (choice.equals("2")) {

                    System.out.print(" Enter the first plant name: ");
                    String name1 = scanner.nextLine();
                    System.out.print(" Enter the second plant name: ");
                    String name2 = scanner.nextLine();

                    final Plant[] plant1 = new Plant[1];
                    final Plant[] plant2 = new Plant[1];

                    Thread t1 = new Thread(() -> plant1[0] = db.findPlant(name1.substring(0).toUpperCase()));
                    Thread t2 = new Thread(() -> plant2[0] = db.findPlant(name2.substring(0).toUpperCase()));

                    t1.start();
                    t2.start();

                    try {
                        t1.join();
                        t2.join();
                    } catch (InterruptedException e) {
                        System.out.println("An error occurred while waiting.");
                    }

                    if (plant1[0] != null && plant2[0] != null) {

                        System.out.println("\nComparison between " + plant1[0].name + " and " + plant2[0].name + ":");


                        System.out.println("Feature       | " + plant1[0].name + "    | " + plant2[0].name);
                        System.out.println("---------------------------------------------");

                        // Comparing the features
                        System.out.println("Light         | " + plant1[0].light + "    | " + plant2[0].light);
                        System.out.println("Watering      | " + plant1[0].watering + "    | " + plant2[0].watering);
                        System.out.println("Temperature   | " + plant1[0].temperature + "    | " + plant2[0].temperature);
                        System.out.println("Fertilizer    | " + plant1[0].fertilizer + "    | " + plant2[0].fertilizer);
                        System.out.println("Pruning       | " + plant1[0].pruning + "    | " + plant2[0].pruning);
                    }
                    else {
                        System.out.println(" One or both plants were not found.");
                    }
                }

                else if (choice.equals("3")) {
                    System.out.println(" Goodbye!");
                    break;
                }

                else {
                    System.out.println(" Invalid choice, please try again.");
                }
            }
        } finally {
            db.close();
        }
    }
}

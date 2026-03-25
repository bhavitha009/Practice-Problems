import java.util.*;

public class PalindromeCheckerApp {

    static final int SIZE = 10;

    // Parking slots
    static String[] parking = new String[SIZE];

    // Entry time tracking
    static HashMap<String, Long> entryTime = new HashMap<>();

    // Park vehicle
    public static String parkVehicle(String vehicle) {

        int index = Math.abs(vehicle.hashCode()) % SIZE;
        int probes = 0;

        while (parking[index] != null) {
            index = (index + 1) % SIZE;
            probes++;
        }

        parking[index] = vehicle;
        entryTime.put(vehicle, System.currentTimeMillis());

        return vehicle + " parked at slot " + index + " (" + probes + " probes)";
    }

    // Exit vehicle
    public static String exitVehicle(String vehicle) {

        for (int i = 0; i < SIZE; i++) {
            if (vehicle.equals(parking[i])) {

                parking[i] = null;

                long duration = (System.currentTimeMillis() - entryTime.get(vehicle)) / 1000;
                entryTime.remove(vehicle);

                return vehicle + " exited. Duration: " + duration + " seconds";
            }
        }

        return "Vehicle not found";
    }

    // Display parking status
    public static void displayParking() {
        System.out.println("Parking Status:");
        for (int i = 0; i < SIZE; i++) {
            System.out.println("Slot " + i + ": " + (parking[i] == null ? "EMPTY" : parking[i]));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(parkVehicle("ABC123"));
        System.out.println(parkVehicle("XYZ999"));
        System.out.println(parkVehicle("ABC124"));

        displayParking();

        Thread.sleep(2000);

        System.out.println(exitVehicle("ABC123"));

        displayParking();
    }
}
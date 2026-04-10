import java.util.*;

public class Main {

    static class Client {
        String name;
        int riskScore;
        double balance;

        Client(String name, int riskScore, double balance) {
            this.name = name;
            this.riskScore = riskScore;
            this.balance = balance;
        }

        public String toString() {
            return name + "(" + riskScore + ")";
        }
    }

    // Bubble Sort (ASC risk)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort (ASC): " + Arrays.toString(arr));
    }

    // Insertion Sort (DESC risk + balance)
    public static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].balance < key.balance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (DESC): " + Arrays.toString(arr));
    }

    // Top 10 clients
    public static void topClients(Client[] arr) {
        System.out.print("Top Clients: ");

        for (int i = 0; i < Math.min(10, arr.length); i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("A", 20, 1000),
                new Client("B", 50, 2000),
                new Client("C", 80, 1500),
                new Client("D", 50, 3000)
        };

        // Bubble Sort
        bubbleSort(clients.clone());

        // Insertion Sort
        insertionSort(clients);

        // Top Clients
        topClients(clients);
    }
}
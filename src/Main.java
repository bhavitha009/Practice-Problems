import java.util.*;

public class Main {

    // ================== LINEAR SEARCH ==================
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search → Found at index " + i +
                        " (" + comparisons + " comparisons)");
                return;
            }
        }

        System.out.println("Linear Search → Not found (" + comparisons + " comparisons)");
    }

    // ================== BINARY SEARCH ==================
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // ================== FLOOR ==================
    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ================== CEILING ==================
    public static int ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    // ================== INSERTION POSITION ==================
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }

        return low;
    }

    // ================== MAIN ==================
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        // Linear Search
        linearSearch(risks, 30);

        // Binary Search
        int index = binarySearch(risks, 50);
        System.out.println("Binary Search → index: " + index);

        // Floor & Ceiling
        System.out.println("Floor(30): " + floor(risks, 30));
        System.out.println("Ceiling(30): " + ceiling(risks, 30));

        // Insertion Position
        System.out.println("Insertion Position of 30: " + insertionPoint(risks, 30));
    }
}
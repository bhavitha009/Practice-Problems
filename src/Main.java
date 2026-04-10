import java.util.*;

public class Main {

    // ================== LINEAR SEARCH ==================
    public static int linearSearch(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear Search → Found at index " + i +
                        " (" + comparisons + " comparisons)");
                return i;
            }
        }

        System.out.println("Linear Search → Not found (" + comparisons + " comparisons)");
        return -1;
    }

    // ================== BINARY SEARCH ==================
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Search → Found at index " + mid +
                        " (" + comparisons + " comparisons)");
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search → Not found (" + comparisons + " comparisons)");
        return -1;
    }

    // ================== COUNT OCCURRENCES ==================
    public static int countOccurrences(String[] arr, String target) {
        int count = 0;

        for (String s : arr) {
            if (s.equals(target)) count++;
        }

        return count;
    }

    // ================== MAIN ==================
    public static void main(String[] args) {

        String[] logs = {"accA", "accB", "accB", "accC"};

        // Linear Search (unsorted allowed)
        linearSearch(logs, "accB");

        // Sort for Binary Search
        Arrays.sort(logs);
        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // Binary Search
        binarySearch(logs, "accB");

        // Count duplicates
        int count = countOccurrences(logs, "accB");
        System.out.println("Occurrences of accB: " + count);
    }
}
import java.util.*;

public class PalindromeCheckerApp {

    // Query → frequency
    static HashMap<String, Integer> searchMap = new HashMap<>();

    // Add/update search query
    public static void updateFrequency(String query) {
        searchMap.put(query, searchMap.getOrDefault(query, 0) + 1);
    }

    // Get top suggestions for prefix
    public static List<String> autocomplete(String prefix) {

        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        // Filter matching prefix
        for (Map.Entry<String, Integer> entry : searchMap.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                list.add(entry);
            }
        }

        // Sort by frequency (descending)
        list.sort((a, b) -> b.getValue() - a.getValue());

        // Get top 10
        List<String> result = new ArrayList<>();
        int count = 0;

        for (Map.Entry<String, Integer> entry : list) {
            result.add(entry.getKey() + " (" + entry.getValue() + ")");
            count++;
            if (count == 10) break;
        }

        return result;
    }

    public static void main(String[] args) {

        // Add search queries
        updateFrequency("java tutorial");
        updateFrequency("javascript");
        updateFrequency("java download");
        updateFrequency("java tutorial");
        updateFrequency("java 21 features");
        updateFrequency("java tutorial");
        updateFrequency("java vs python");

        // Search
        List<String> suggestions = autocomplete("jav");

        System.out.println("Suggestions:");
        for (String s : suggestions) {
            System.out.println(s);
        }
    }
}
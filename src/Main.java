import java.util.*;

public class PalindromeCheckerApp {

    // ================== Problem 1 ==================

    static HashMap<String, Integer> users = new HashMap<>();
    static HashMap<String, Integer> attempts = new HashMap<>();

    // Check availability
    public static boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    // Register user
    public static void registerUser(String username, int userId) {
        users.put(username, userId);
    }

    // Suggest alternatives
    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        suggestions.add(username + "1");
        suggestions.add(username + "123");
        suggestions.add(username + "_01");
        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // Get most attempted username
    public static String getMostAttempted() {
        String maxUser = "";
        int maxCount = 0;

        for (String user : attempts.keySet()) {
            if (attempts.get(user) > maxCount) {
                maxCount = attempts.get(user);
                maxUser = user;
            }
        }
        return maxUser + " (" + maxCount + " attempts)";
    }

    // ================== MAIN ==================

    public static void main(String[] args) {

        // Pre-existing users
        registerUser("john_doe", 1);
        registerUser("admin", 2);

        // Check availability
        System.out.println(checkAvailability("john_doe"));   // false
        System.out.println(checkAvailability("jane_smith")); // true

        // Suggestions
        System.out.println(suggestAlternatives("john_doe"));

        // Multiple attempts
        checkAvailability("admin");
        checkAvailability("admin");
        checkAvailability("admin");

        // Most attempted
        System.out.println(getMostAttempted());
    }
}
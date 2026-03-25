import java.util.*;

public class PalindromeCheckerApp {

    // Page → total visits
    static HashMap<String, Integer> pageViews = new HashMap<>();

    // Page → unique users
    static HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // Traffic source → count
    static HashMap<String, Integer> trafficSources = new HashMap<>();

    // Process event
    public static void processEvent(String url, String userId, String source) {

        // Count page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // Track unique users
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // Track traffic source
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // Get Top 10 pages
    public static void getTopPages() {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(pageViews.entrySet());

        // Sort descending
        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Top Pages:");

        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println((count + 1) + ". " + page + " - " + views +
                    " views (" + unique + " unique)");

            count++;
            if (count == 10) break;
        }
    }

    // Show traffic sources %
    public static void getTrafficSources() {

        int total = 0;
        for (int count : trafficSources.values()) {
            total += count;
        }

        System.out.println("\nTraffic Sources:");

        for (String source : trafficSources.keySet()) {
            int count = trafficSources.get(source);
            double percent = (count * 100.0) / total;

            System.out.println(source + ": " + percent + "%");
        }
    }

    // Dashboard
    public static void getDashboard() {
        getTopPages();
        getTrafficSources();
    }

    public static void main(String[] args) {

        // Simulate events
        processEvent("/article/news", "user1", "google");
        processEvent("/article/news", "user2", "facebook");
        processEvent("/article/news", "user1", "google");
        processEvent("/sports/match", "user3", "direct");
        processEvent("/sports/match", "user4", "google");
        processEvent("/sports/match", "user5", "google");

        // Show dashboard
        getDashboard();
    }
}
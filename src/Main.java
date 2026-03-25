import java.util.*;

public class Main {

    // L1 Cache (LRU using LinkedHashMap)
    static LinkedHashMap<String, String> L1 = new LinkedHashMap<>(5, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > 5;
        }
    };

    // L2 Cache
    static HashMap<String, String> L2 = new HashMap<>();

    // L3 Database (simulated)
    static HashMap<String, String> L3 = new HashMap<>();

    // Access count (for promotion)
    static HashMap<String, Integer> accessCount = new HashMap<>();

    // Get video
    public static String getVideo(String videoId) {

        // L1 Check
        if (L1.containsKey(videoId)) {
            return "L1 HIT → " + L1.get(videoId);
        }

        // L2 Check
        if (L2.containsKey(videoId)) {
            String data = L2.get(videoId);

            // Promote to L1
            L1.put(videoId, data);

            return "L2 HIT → Promoted to L1 → " + data;
        }

        // L3 (Database)
        if (L3.containsKey(videoId)) {
            String data = L3.get(videoId);

            // Add to L2
            L2.put(videoId, data);

            accessCount.put(videoId, 1);

            return "L3 HIT → Added to L2 → " + data;
        }

        return "Video not found";
    }

    // Add video to database
    public static void addVideo(String videoId, String data) {
        L3.put(videoId, data);
    }

    // Show stats
    public static void getStats() {
        System.out.println("L1 size: " + L1.size());
        System.out.println("L2 size: " + L2.size());
        System.out.println("L3 size: " + L3.size());
    }

    public static void main(String[] args) {

        // Add videos to database
        addVideo("video1", "Movie A");
        addVideo("video2", "Movie B");

        // Access flow
        System.out.println(getVideo("video1")); // L3 → L2
        System.out.println(getVideo("video1")); // L2 → L1
        System.out.println(getVideo("video1")); // L1 hit

        System.out.println(getVideo("video2")); // L3 → L2

        // Stats
        getStats();
    }
}
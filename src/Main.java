import java.util.*;

public class PalindromeCheckerApp {

    // DNS Entry class
    static class DNSEntry {
        String ipAddress;
        long expiryTime;

        DNSEntry(String ipAddress, long ttlMillis) {
            this.ipAddress = ipAddress;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    // Cache: domain → DNSEntry
    static HashMap<String, DNSEntry> cache = new HashMap<>();

    static int hits = 0;
    static int misses = 0;

    // Resolve domain
    public static String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return "Cache HIT → " + entry.ipAddress;
            } else {
                cache.remove(domain); // remove expired
            }
        }

        // Cache MISS → simulate DNS lookup
        misses++;
        String newIP = fetchFromServer(domain);

        cache.put(domain, new DNSEntry(newIP, 5000)); // TTL = 5 sec

        return "Cache MISS → " + newIP;
    }

    // Simulate upstream DNS fetch
    public static String fetchFromServer(String domain) {
        return "192.168." + (int)(Math.random()*255) + "." + (int)(Math.random()*255);
    }

    // Cache statistics
    public static void getStats() {
        int total = hits + misses;
        double hitRate = (total == 0) ? 0 : (hits * 100.0 / total);

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) throws InterruptedException {

        // First call → MISS
        System.out.println(resolve("google.com"));

        // Second call → HIT
        System.out.println(resolve("google.com"));

        // Wait for TTL to expire
        Thread.sleep(6000);

        // After expiry → MISS again
        System.out.println(resolve("google.com"));

        // Stats
        getStats();
    }
}
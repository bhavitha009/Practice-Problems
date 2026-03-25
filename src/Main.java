import java.util.*;

public class PalindromeCheckerApp {

    // Token Bucket class
    static class TokenBucket {
        int tokens;
        int maxTokens;
        int refillRate; // tokens per second
        long lastRefillTime;

        TokenBucket(int maxTokens, int refillRate) {
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.tokens = maxTokens;
            this.lastRefillTime = System.currentTimeMillis();
        }

        // Refill tokens based on time passed
        void refill() {
            long now = System.currentTimeMillis();
            long secondsPassed = (now - lastRefillTime) / 1000;

            if (secondsPassed > 0) {
                int newTokens = (int) secondsPassed * refillRate;
                tokens = Math.min(maxTokens, tokens + newTokens);
                lastRefillTime = now;
            }
        }

        // Try consuming token
        boolean allowRequest() {
            refill();
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }
    }

    // Client → TokenBucket
    static HashMap<String, TokenBucket> clients = new HashMap<>();

    // Rate limit check
    public static String checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(5, 1)); // 5 max, 1/sec

        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            return "Allowed (" + bucket.tokens + " tokens left)";
        } else {
            return "Denied (Rate limit exceeded)";
        }
    }

    // Get client status
    public static void getStatus(String clientId) {
        TokenBucket bucket = clients.get(clientId);

        if (bucket != null) {
            System.out.println("Client: " + clientId +
                    " | Tokens left: " + bucket.tokens +
                    " | Max: " + bucket.maxTokens);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String client = "abc123";

        // Burst requests
        for (int i = 0; i < 7; i++) {
            System.out.println(checkRateLimit(client));
        }

        // Wait for refill
        Thread.sleep(3000);

        System.out.println("\nAfter waiting:");

        // Try again
        for (int i = 0; i < 3; i++) {
            System.out.println(checkRateLimit(client));
        }

        // Status
        getStatus(client);
    }
}
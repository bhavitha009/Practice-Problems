import java.util.*;

public class PalindromeCheckerApp {

    // Transaction class
    static class Transaction {
        int id;
        int amount;
        String merchant;
        String time;

        Transaction(int id, int amount, String merchant, String time) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.time = time;
        }
    }

    // ================== Two Sum ==================
    public static void findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction t2 = map.get(complement);

                System.out.println("Pair Found → (" + t2.id + ", " + t.id + ")");
                return;
            }

            map.put(t.amount, t);
        }

        System.out.println("No pair found");
    }

    // ================== Duplicate Detection ==================
    public static void detectDuplicates(List<Transaction> list) {

        HashMap<String, List<Integer>> map = new HashMap<>();

        for (Transaction t : list) {
            String key = t.amount + "_" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t.id);
        }

        System.out.println("Duplicates:");

        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                System.out.println(key + " → Transactions: " + map.get(key));
            }
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1, 500, "StoreA", "10:00"));
        transactions.add(new Transaction(2, 300, "StoreB", "10:15"));
        transactions.add(new Transaction(3, 200, "StoreC", "10:30"));
        transactions.add(new Transaction(4, 500, "StoreA", "11:00")); // duplicate

        // Two Sum
        findTwoSum(transactions, 500); // 300 + 200

        // Duplicate detection
        detectDuplicates(transactions);
    }
}
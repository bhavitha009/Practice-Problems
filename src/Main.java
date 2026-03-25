import java.util.*;

public class PalindromeCheckerApp {

    // Inventory: product → stock count
    static HashMap<String, Integer> inventory = new HashMap<>();

    // Waiting list: product → queue of userIds
    static HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    // Check stock
    public static String checkStock(String product) {
        int stock = inventory.getOrDefault(product, 0);
        return product + " → " + stock + " units available";
    }

    // Purchase item
    public synchronized static String purchaseItem(String product, int userId) {

        int stock = inventory.getOrDefault(product, 0);

        if (stock > 0) {
            inventory.put(product, stock - 1);
            return "User " + userId + " → Purchase Success, remaining: " + (stock - 1);
        } else {
            waitingList.putIfAbsent(product, new LinkedList<>());
            waitingList.get(product).add(userId);

            int position = waitingList.get(product).size();
            return "User " + userId + " → Added to waiting list, position #" + position;
        }
    }

    public static void main(String[] args) {

        // Initial stock
        inventory.put("IPHONE15_256GB", 3);

        // Check stock
        System.out.println(checkStock("IPHONE15_256GB"));

        // Purchase requests
        System.out.println(purchaseItem("IPHONE15_256GB", 101));
        System.out.println(purchaseItem("IPHONE15_256GB", 102));
        System.out.println(purchaseItem("IPHONE15_256GB", 103));

        // Stock finished → waiting list
        System.out.println(purchaseItem("IPHONE15_256GB", 104));
        System.out.println(purchaseItem("IPHONE15_256GB", 105));
    }
}
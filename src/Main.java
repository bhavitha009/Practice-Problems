import java.util.*;

public class PalindromeCheckerApp {

    // n-gram size
    static final int N = 3;

    // Map: n-gram → set of document IDs
    static HashMap<String, Set<String>> ngramMap = new HashMap<>();

    // Store document text
    static HashMap<String, String> documents = new HashMap<>();

    // Add document and build n-grams
    public static void addDocument(String docId, String text) {
        documents.put(docId, text);

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < N; j++) {
                gram.append(words[i + j]).append(" ");
            }

            String ngram = gram.toString().trim();

            ngramMap.putIfAbsent(ngram, new HashSet<>());
            ngramMap.get(ngram).add(docId);
        }
    }

    // Compare document with existing ones
    public static void analyzeDocument(String newDocId, String text) {

        String[] words = text.split(" ");
        HashMap<String, Integer> matchCount = new HashMap<>();

        int totalNgrams = 0;

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < N; j++) {
                gram.append(words[i + j]).append(" ");
            }

            String ngram = gram.toString().trim();
            totalNgrams++;

            if (ngramMap.containsKey(ngram)) {
                for (String docId : ngramMap.get(ngram)) {
                    matchCount.put(docId, matchCount.getOrDefault(docId, 0) + 1);
                }
            }
        }

        System.out.println("Total n-grams: " + totalNgrams);

        // Calculate similarity
        for (String docId : matchCount.keySet()) {
            int matches = matchCount.get(docId);
            double similarity = (matches * 100.0) / totalNgrams;

            System.out.println("Matched with " + docId + " → " + matches + " n-grams");
            System.out.println("Similarity: " + similarity + "%");

            if (similarity > 50) {
                System.out.println("⚠️ PLAGIARISM DETECTED");
            }
        }
    }

    public static void main(String[] args) {

        // Add existing documents
        addDocument("doc1", "this is a sample document for testing plagiarism detection");
        addDocument("doc2", "this document is used for plagiarism testing system");

        // Analyze new document
        analyzeDocument("doc3", "this is a sample document used for testing plagiarism");
    }
}
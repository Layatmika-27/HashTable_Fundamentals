import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}

public class AutocompleteSystem {

    private static TrieNode root = new TrieNode();
    private static HashMap<String, Integer> frequencyMap = new HashMap<>();

    // Insert word into Trie
    public static void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }

        current.isWord = true;

        // Update frequency dynamically
        frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
    }

    // Search prefix
    public static List<String> search(String prefix) {
        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            current = current.children.get(ch);
        }

        List<String> results = new ArrayList<>();
        collectWords(current, prefix, results);

        // Sort by frequency descending
        results.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        // Return top 10
        if (results.size() > 10) {
            return results.subList(0, 10);
        }

        return results;
    }

    // DFS to collect words
    private static void collectWords(TrieNode node, String word, List<String> results) {
        if (node.isWord) {
            results.add(word);
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectWords(entry.getValue(), word + entry.getKey(), results);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of initial queries: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter queries:");
        for (int i = 0; i < n; i++) {
            String query = sc.nextLine();
            insert(query);
        }

        while (true) {
            System.out.print("\nEnter prefix to search (or 'exit'): ");
            String prefix = sc.nextLine();

            if (prefix.equalsIgnoreCase("exit")) break;

            List<String> suggestions = search(prefix);

            if (suggestions.isEmpty()) {
                System.out.println("No suggestions found.");
            } else {
                System.out.println("Top Suggestions:");
                for (String s : suggestions) {
                    System.out.println(s + " (freq: " + frequencyMap.get(s) + ")");
                }
            }

            // Dynamic update (simulate user selecting first suggestion)
            if (!suggestions.isEmpty()) {
                insert(suggestions.get(0));
            }
        }

        sc.close();
    }
}

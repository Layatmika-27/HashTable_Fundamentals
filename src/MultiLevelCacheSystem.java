import java.util.*;

public class MultiLevelCacheSystem {

    private static int capacity;
    private static int hits = 0;
    private static int misses = 0;

    private static LinkedHashMap<String, String> cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter L1 Cache capacity: ");
        capacity = sc.nextInt();
        sc.nextLine();

        cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > capacity;
            }
        };

        while (true) {
            System.out.println("\n1. Put  2. Get  3. Stats  4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter key: ");
                String key = sc.nextLine();
                System.out.print("Enter value: ");
                String value = sc.nextLine();
                cache.put(key, value);
                System.out.println("Inserted.");
            }

            else if (choice == 2) {
                System.out.print("Enter key: ");
                String key = sc.nextLine();
                if (cache.containsKey(key)) {
                    hits++;
                    System.out.println("Value: " + cache.get(key));
                } else {
                    misses++;
                    System.out.println("Cache Miss.");
                }
            }

            else if (choice == 3) {
                System.out.println("Hits: " + hits);
                System.out.println("Misses: " + misses);
                double ratio = (hits + misses) == 0 ? 0 :
                        (double) hits / (hits + misses);
                System.out.println("Hit Ratio: " + ratio);
            }

            else break;
        }
    }
}

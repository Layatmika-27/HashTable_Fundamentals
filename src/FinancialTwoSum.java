import java.util.*;

class Transaction {
    int amount;
    long timestamp;

    Transaction(int amount) {
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
    }
}

public class FinancialTwoSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, List<Transaction>> map = new HashMap<>();

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();

        System.out.print("Enter target amount: ");
        int target = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter transaction amount: ");
            int amount = sc.nextInt();

            Transaction tx = new Transaction(amount);

            int complement = target - amount;

            if (map.containsKey(complement)) {
                for (Transaction t : map.get(complement)) {
                    System.out.println("Fraud Pair Found: " + amount + " and " + complement);
                }
            }

            map.computeIfAbsent(amount, k -> new ArrayList<>()).add(tx);
        }
    }
}

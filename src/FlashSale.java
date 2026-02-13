import java.util.*;

public class FlashSale {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> stock = new HashMap<>();

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++){
            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            System.out.print("Enter stock quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();
            stock.put(name, qty);
        }

        System.out.print("Enter product to purchase: ");
        String buy = sc.nextLine();

        if(stock.containsKey(buy) && stock.get(buy) > 0){
            stock.put(buy, stock.get(buy) - 1);
            System.out.println("Purchase successful! Remaining: " + stock.get(buy));
        } else {
            System.out.println("Out of stock!");
        }
    }
}

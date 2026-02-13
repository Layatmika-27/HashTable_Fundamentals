import java.util.*;

public class RealTimeAnalytics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> pageViews = new HashMap<>();

        System.out.print("Enter number of visits: ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++){
            System.out.print("Enter page URL: ");
            String page = sc.nextLine();
            pageViews.put(page, pageViews.getOrDefault(page, 0) + 1);
        }

        System.out.println("Page View Counts:");
        for(String key : pageViews.keySet()){
            System.out.println(key + " -> " + pageViews.get(key));
        }
    }
}

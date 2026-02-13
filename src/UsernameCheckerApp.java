import java.util.*;

public class UsernameCheckerApp {
    private static HashMap<String, Integer> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of existing users: ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++){
            System.out.print("Enter username: ");
            String name = sc.nextLine();
            users.put(name, i+1);
        }

        System.out.print("Enter username to check: ");
        String check = sc.nextLine();

        if(users.containsKey(check)){
            System.out.println("Username already taken.");
            System.out.println("Suggestions:");
            for(int i = 1; i <= 3; i++){
                System.out.println(check + i);
            }
        } else {
            System.out.println("Username available.");
        }
    }
}

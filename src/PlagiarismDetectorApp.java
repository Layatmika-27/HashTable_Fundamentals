import java.util.*;

public class PlagiarismDetectorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> ngrams = new HashMap<>();

        System.out.print("Enter first text: ");
        String text1 = sc.nextLine();
        System.out.print("Enter second text: ");
        String text2 = sc.nextLine();

        String[] words1 = text1.split(" ");
        for(int i = 0; i < words1.length - 1; i++){
            String gram = words1[i] + " " + words1[i+1];
            ngrams.put(gram, 1);
        }

        int match = 0;
        String[] words2 = text2.split(" ");
        for(int i = 0; i < words2.length - 1; i++){
            String gram = words2[i] + " " + words2[i+1];
            if(ngrams.containsKey(gram))
                match++;
        }

        System.out.println("Matching 2-grams: " + match);
    }
}

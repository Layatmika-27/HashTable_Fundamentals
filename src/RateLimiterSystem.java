import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class TokenBucket {
    private int tokens;
    private final int maxTokens;
    private final int refillRatePerHour;
    private long lastRefillTime;

    public TokenBucket(int maxTokens, int refillRatePerHour) {
        this.maxTokens = maxTokens;
        this.refillRatePerHour = refillRatePerHour;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refill();

        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refill() {
        long currentTime = System.currentTimeMillis();
        long elapsedMillis = currentTime - lastRefillTime;

        long hoursPassed = elapsedMillis / (60 * 60 * 1000);

        if (hoursPassed > 0) {
            int refillTokens = (int)(hoursPassed * refillRatePerHour);
            tokens = Math.min(maxTokens, tokens + refillTokens);
            lastRefillTime = currentTime;
        }
    }
}

public class RateLimiterSystem {

    private static final ConcurrentHashMap<String, TokenBucket> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter max tokens per client per hour: ");
        int maxTokens = sc.nextInt();

        System.out.print("Enter refill rate per hour: ");
        int refillRate = sc.nextInt();
        sc.nextLine();

        while (true) {
            System.out.print("\nEnter client ID (or 'exit'): ");
            String clientId = sc.nextLine();

            if (clientId.equalsIgnoreCase("exit")) break;

            clients.putIfAbsent(clientId, new TokenBucket(maxTokens, refillRate));

            boolean allowed = clients.get(clientId).allowRequest();

            if (allowed) {
                System.out.println("Request Allowed");
            } else {
                System.out.println("Rate Limit Exceeded");
            }
        }

        sc.close();
    }
}

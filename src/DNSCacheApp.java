import java.util.*;

class DNSEntry {
    String ip;
    long expiry;

    DNSEntry(String ip, long ttl){
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttl * 1000;
    }

    boolean isExpired(){
        return System.currentTimeMillis() > expiry;
    }
}

public class DNSCacheApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, DNSEntry> cache = new HashMap<>();

        System.out.print("Enter domain: ");
        String domain = sc.nextLine();
        System.out.print("Enter IP: ");
        String ip = sc.nextLine();
        System.out.print("Enter TTL (seconds): ");
        int ttl = sc.nextInt();

        cache.put(domain, new DNSEntry(ip, ttl));

        if(cache.containsKey(domain)){
            DNSEntry entry = cache.get(domain);
            if(!entry.isExpired())
                System.out.println("IP Address: " + entry.ip);
            else
                System.out.println("Entry expired.");
        }
    }
}

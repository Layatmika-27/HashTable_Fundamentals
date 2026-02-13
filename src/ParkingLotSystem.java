import java.util.*;

public class ParkingLotSystem {

    private static String[] table;
    private static int size;
    private static int occupied = 0;

    private static int hash(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    private static int park(String plate) {
        if (occupied == size) return -1;

        int index = hash(plate);
        int start = index;

        while (table[index] != null) {
            if (table[index].equals(plate)) {
                return index; // already parked
            }
            index = (index + 1) % size;

            if (index == start) return -1;
        }

        table[index] = plate;
        occupied++;
        return index;
    }

    private static boolean remove(String plate) {
        int index = hash(plate);
        int start = index;

        while (table[index] != null) {
            if (table[index].equals(plate)) {
                table[index] = null;
                occupied--;
                return true;
            }
            index = (index + 1) % size;
            if (index == start) break;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter parking lot size: ");
        size = sc.nextInt();
        sc.nextLine();

        table = new String[size];

        while (true) {
            System.out.println("\n1. Park  2. Remove  3. Status  4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter license plate: ");
                String plate = sc.nextLine();
                int slot = park(plate);
                if (slot == -1)
                    System.out.println("Parking Full!");
                else
                    System.out.println("Parked at slot: " + slot);
            }

            else if (choice == 2) {
                System.out.print("Enter license plate: ");
                String plate = sc.nextLine();
                if (remove(plate))
                    System.out.println("Car removed.");
                else
                    System.out.println("Car not found.");
            }

            else if (choice == 3) {
                System.out.println("Occupied: " + occupied + "/" + size);
                System.out.println("Load Factor: " + (double) occupied / size);
            }

            else break;
        }
    }
}

import java.util.HashMap;
import java.util.Scanner;

public class Registration {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            String name = scan.next();
            Integer count = map.get(name);
            if (count == null) {
                map.put(name, 1);
                System.out.println("OK");
            } else {
                map.put(name, count + 1);
                System.out.println(name + count);
            }
        }
    }
}

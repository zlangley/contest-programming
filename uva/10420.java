import java.util.*;

class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> loverCount = new TreeMap<String, Integer>();
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            String country = scan.next();
            scan.nextLine();

            Integer count = loverCount.get(country);
            if (count == null)
                loverCount.put(country, 1);
            else
                loverCount.put(country, count + 1);
        }

        while (loverCount.size() > 0) {
            Map.Entry<String, Integer> pair = loverCount.pollFirstEntry();
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}

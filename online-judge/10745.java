import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedList<String> dominators = new LinkedList<String>();

        while (scan.hasNext()) {
            String x = scan.next();

            boolean dominated = false;
            Iterator<String> it = dominators.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (s.length() == x.length())
                    continue;

                if (s.length() < x.length() && dominates(x, s)) {
                    it.remove();
                } else if (dominates(s, x)) {
                    dominated = true;
                    break;
                }
            }
            if (!dominated) {
                dominators.add(x);
            }
        }
        TreeSet<String> newSet = new TreeSet<String>();
        newSet.addAll(dominators);
        for (String s : newSet)
            System.out.println(s);
    }

    // assume |x| > |y|
    public static boolean dominates(String x, String y) {
        int[] count = new int[26];
        int needsKilling = 0;

        for (int i = 0; i < y.length(); i++) {
            int let = y.charAt(i) - 'a';
            if (count[let] == 0)
                needsKilling++;
            count[let]++;
        }

        for (int i = 0; i < x.length(); i++) {
            int let = x.charAt(i) - 'a';
            if (count[let] == 1)
                needsKilling--;
            count[let]--;
        }

        return needsKilling == 0;
    }
}

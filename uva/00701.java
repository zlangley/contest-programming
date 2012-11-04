import java.util.Scanner;

// The Archeologists' Dilemma.
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double log10 = Math.log(10)/Math.log(2);

        while (scan.hasNext()) {
            String next = scan.next();
            int n = Integer.parseInt(next);
            int m = next.length();

            int k;
            do {
                m++;
                k = (int)Math.ceil(lg(n) + m * lg(10));
            } while (k > m * lg(10) + lg(n + 1));

            System.out.println(k);
        }
    }

    public static double lg(int a) {
        return Math.log(a) / Math.log(2);
    }
}

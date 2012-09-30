import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int[] x = new int[n];

        for (int i = 0; i < n; i++)
            x[i] = scan.nextInt();

        for (int a = 0; a < 10000; a++) {
            for (int b = 0; b < 10000; b++) {
                boolean found = false;
                for (int i = 0; i < x.length - 1; i++) {
                    found = true;
                    if (x[i + 1] != ((a*a % 10001) * x[i] + a * b + b) % 10001) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    for (int y : x) {
                        System.out.println((a*y + b) % 10001);
                    }
                        return;
                }
            }
        }
    }
}

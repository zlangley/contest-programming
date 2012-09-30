import java.util.Scanner;

// Triangular Sums.
public class B {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int m = scan.nextInt();

            long sum = 0;
            for (int k = 1; k <= m; k++) {
                sum += k*(k + 1)*(k + 2)/2;
            }
            System.out.println((i + 1) + " " + m + " " + sum);
        }
    }
}

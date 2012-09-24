import java.math.BigInteger;
import java.util.Scanner;

public class H {
    private static long[] factorial = new long[21];
    static {
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++)
            factorial[i] = factorial[i-1] * i;
    }

    public static long binomial(int n, int k) {
        return factorial[n]/(factorial[n-k]*factorial[k]);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int lines = scan.nextInt();
        for (int i = 1; i <= lines; i++) {
            scan.nextInt();
            int n = scan.nextInt();
            int k = scan.nextInt();

            if (k == 1)
                System.out.println(i + " " + factorial[n - 1]);
            else {
                long result = 0;
                for (int j = 2; j <= k; j++)
                    result += binomial(k-2, j-2)*factorial[j-1]*factorial[n-j];
                System.out.println(i + " " + result);
            }
        }
    }
}

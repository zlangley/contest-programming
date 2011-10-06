import java.util.*;

public class ProblemD {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int k = 0; k < n; k++) {
            int a = scan.nextInt();

            int[][] table = new int[a + 1][a + 1];
            for (int i = 1; i < table.length; i++) {
                for (int j = 1; j < table.length; j++) {
                    if (j == 1)
                        table[i][j] = (int)Math.pow(2, i) - 1;
                    else if (j <= i)
                        table[i][j] = j * table[i - 1][j - 1] + (j + 1) * table[i - 1][j];
                }
            }

            long sum = 0;
            for (int i = 1; i <= a; i++)
                sum += table[a][i];

            System.out.println((k + 1) + " " + a + " " + sum);
        }
    }
}

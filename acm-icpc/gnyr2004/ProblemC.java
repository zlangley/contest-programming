import java.util.*;

public class ProblemC {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int lines = scan.nextInt();
        for (int i = 0; i < lines; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            // table[a][b] = the number of lotto sequences with max value a of length b
            long[][] table = new long[m + 1][n + 1];
            for (int j = 1; j < table.length; j++)
                table[j][1] = j;
            for (int j = 2; j < table.length; j++) {
                for (int k = 2; k < table[j].length; k++) {
                    table[j][k] = table[j - 1][k] + table[j/2][k - 1];
                }
            }

            System.out.println("Data set " + (i + 1) + ": " + n + " " + m + " " + table[m][n]);
        }
    }
}

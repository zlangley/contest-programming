import java.util.Scanner;

public class LeastRound {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int[][] mat = new int[n][n];
        int zeroI = -1, zeroJ = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = scan.nextInt();
                if (mat[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
           }
        }

        int[][] minTwos = new int[n][n];
        int[][] minFives = new int[n][n];

        minTwos[0][0] = countMultiplesOf(mat[0][0], 2);
        minFives[0][0] = countMultiplesOf(mat[0][0], 5);
        for (int i = 1; i < n; i++) {
            minTwos[i][0] = minTwos[i-1][0] + countMultiplesOf(mat[i][0], 2);
            minTwos[0][i] = minTwos[0][i-1] + countMultiplesOf(mat[0][i], 2);
            minFives[i][0] = minFives[i-1][0] + countMultiplesOf(mat[i][0], 5);
            minFives[0][i] = minFives[0][i-1] + countMultiplesOf(mat[0][i], 5);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                minTwos[i][j] = Math.min(minTwos[i-1][j], minTwos[i][j-1]) + countMultiplesOf(mat[i][j], 2);
                minFives[i][j] = Math.min(minFives[i-1][j], minFives[i][j-1]) + countMultiplesOf(mat[i][j], 5);
            }
        }

        int[][] winner = (minTwos[n-1][n-1] < minFives[n-1][n-1] ? minTwos : minFives);
        if (zeroI != -1 && winner[n-1][n-1] != 0) {
            System.out.println(1);
            for (int k = 0; k < zeroI; k++)
                System.out.print('D');
            for (int k = 0; k < zeroJ; k++)
                System.out.print('R');
            for (int k = zeroI; k < n - 1; k++)
                System.out.print('D');
            for (int k = zeroJ; k < n - 1; k++)
                System.out.print('R');
            System.out.println();
            return;
        }

        StringBuilder path = new StringBuilder();
        int i = n - 1, j = n - 1;
        while (i > 0 && j > 0) {
            if (winner[i-1][j] < winner[i][j-1]) {
                path.insert(0, 'D');
                i--;
            } else {
                path.insert(0, 'R');
                j--;
            }
        }

        while (i-- > 0)
            path.insert(0, 'D');
        while (j-- > 0)
            path.insert(0, 'R');

        System.out.println(winner[n-1][n-1]);
        System.out.println(path);
    }

    public static int countMultiplesOf(int n, int k) {
        if (n == 0)
            return 100000;

        int count = 0;
        while (n % k == 0) {
            count++;
            n /= k;
        }
        return count;
    }
}

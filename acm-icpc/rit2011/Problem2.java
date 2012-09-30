import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int m = scan.nextInt();
        int n = scan.nextInt();
        int[][] grid = new int[m + 1][n + 1];

        int k = scan.nextInt();
        for (int i = 0; i < k; i++) {
            int r1 = scan.nextInt() - 1;
            int c1 = scan.nextInt() - 1;
            int r2 = scan.nextInt() - 1;
            int c2 = scan.nextInt() - 1;

            grid[r1][c1]++;
            grid[r1][c2 + 1]--;
            grid[r2 + 1][c1]--;
            grid[r2 + 1][c2 + 1]++;
        }

        int[][] ans = new int[m + 1][n + 1];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (r > 0)
                    grid[r][c] += grid[r - 1][c];
                if (c > 0)
                    ans[r][c] += ans[r][c - 1] + grid[r][c];
                else
                    ans[r][c] = grid[r][c];
            }
        }

        for (int r = 0; r < ans.length - 1; r++) {
            for (int c = 0; c < ans[r].length - 1; c++)
                System.out.print(ans[r][c] + " ");
            System.out.println();
        }
    }
}

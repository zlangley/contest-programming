import java.util.*;

// Chopsticks.
class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int probs = scan.nextInt();

		for (int i = 0; i < probs; i++) {
			int k = scan.nextInt();
			int n = scan.nextInt();

			int[] sticks = new int[n + 1];
			for (int j = 0; j < n; j++)
				sticks[sticks.length - j - 1] = scan.nextInt();

			int[][] m = new int[n + 1][k + 9];
			for (int c = 1; c < m[0].length; c++)
				for (int r = 0; r < m.length; r++)
					m[r][c] = Integer.MAX_VALUE;

			for (int c = 1; c < m[0].length; c++) {
				for (int r = 3*c; r < m.length; r++) {
					int sqrtBad = sticks[r - 1] - sticks[r];
					m[r][c] = Math.min(m[r - 1][c], m[r - 2][c - 1] + sqrtBad*sqrtBad);
				}
			}

			System.out.println(m[n][k + 8]);
		}
	}
}

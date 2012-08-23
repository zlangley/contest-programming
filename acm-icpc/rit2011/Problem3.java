import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int[] pieces = new int[n];

		int sum = 0;
		for (int i = 0; i < n; i++) {
			pieces[i] = scan.nextInt();
			sum += pieces[i];
		}

		if (sum % 2 == 1) {
			System.out.println("NO");
			return;
		}

		boolean[][][][] m = new boolean[n + 1][sum/2 + 1][sum/2 + 1][sum/2 + 1];
		for (int i = 0; i < m.length; i++)
			m[i][0][0][0] = true;

		for (int i = 1; i < m.length; i++) {
			for (int l = 0; l < m[i].length; l++) {
				for (int t = 0; t < m[i][l].length; t++) {
					for (int r = 0; r < m[i][l][t].length; r++) {
						m[i][l][t][r] = m[i - 1][l][t][r];
						if (l >= pieces[i - 1])
							m[i][l][t][r] = m[i][l][t][r] || m[i - 1][l - pieces[i - 1]][t][r];
						if (t >= pieces[i - 1])
							m[i][l][t][r] = m[i][l][t][r] || m[i - 1][l][t - pieces[i - 1]][r];
						if (r >= pieces[i - 1])
							m[i][l][t][r] = m[i][l][t][r] || m[i - 1][l][t][r - pieces[i - 1]];
					}
				}
			}
		}

		for (int s = sum/4; s >= 0; s--) {
			if (m[n][s][sum/2 - s][s]) {
				System.out.println("YES");
				System.out.println(sum/2 - 2*s);
				return;
			}
		}

		System.out.println("NO");
	}
}

import java.util.*;

public class ProblemF {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			int m = scan.nextInt();

			int sum = 3;
			for (int j = 1; j <= m; j++)
				for (int k = j + 1; k <= m; k++)
					if (gcd(j, k) == 1)
						sum += 2;

			System.out.println((i + 1) + " " + m + " " + sum);
		}
	}

	static int[][] gcd = new int[1001][1001];
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		if (gcd[a][b] != 0)
			return gcd[a][b];

		gcd[a][b] = gcd(b, a % b);
		return gcd[a][b];
	}
}

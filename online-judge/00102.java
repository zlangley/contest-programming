import java.util.Scanner;

// Ecological Bin Packing.
class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			int[][] bins = new int[3][3];
			int[] sum = new int[3];
			for (int i = 0; i < 3; i++) {
				bins[i][0] = scan.nextInt();
				bins[i][1] = scan.nextInt();
				bins[i][2] = scan.nextInt();
				sum[0] += bins[i][0];
				sum[1] += bins[i][1];
				sum[2] += bins[i][2];
			}

			int minCost = Integer.MAX_VALUE;
			char[] maxOrder = new char[3];

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == j)
						continue;
					for (int k = 0; k < 3; k++) {
						if (k == i || k == j)
							continue;

						int moves = sum[0] - bins[i][0] + sum[1] - bins[j][1] + sum[2] - bins[k][2];

						if (moves < minCost) {
							minCost = moves;
							maxOrder[i] = 'B';
							maxOrder[j] = 'G';
							maxOrder[k] = 'C';
						} else if (moves == minCost) {
							char[] order = new char[3];
							order[i] = 'B';
							order[j] = 'G';
							order[k] = 'C';
							if (order[0] < maxOrder[0])
								maxOrder = order;
							else if (order[0] == maxOrder[0] && order[1] < maxOrder[1])
								maxOrder = order;
						}
					}
				}
			}

			System.out.println(new String(maxOrder) + " " + minCost);
		}
	}
}

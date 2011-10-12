import java.util.Scanner;

// Unidirectional TSP.
class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			int rows = scan.nextInt();
			int cols = scan.nextInt();

			long[][] m = new long[rows][cols];

			for (int r = 0; r < rows; r++) {
				// Mirror the array so lexicographically smallest solution is easy to find.
				for (int c = 0; c < cols; c++)
					m[r][cols - c - 1] = scan.nextInt();
			}

			for (int c = 1; c < cols; c++) {
				for (int r = 0; r < rows; r++) {
					int upRow = (rows + r - 1) % rows;
					int downRow = (r + 1) % rows;
					m[r][c] += Math.min(Math.min(m[r][c - 1], m[upRow][c - 1]), m[downRow][c - 1]);
				}
			}

			long minValue = Integer.MAX_VALUE;
			int minRow = -1;
			for (int r = 0; r < rows; r++) {
				if (m[r][cols - 1] < minValue) {
					minRow = r;
					minValue = m[r][cols - 1];
				}
			}

			System.out.print(minRow + 1);
			int r = minRow;
			int c = cols - 1;
			while (c != 0) {
				// Must be careful here to find solution with smallest 
				// lexicographical order.
				int upRow = (m.length + r - 1) % m.length;
				int downRow = (r + 1) % m.length;

				int lowRow = upRow;
				int midRow = r;
				int highRow = downRow;

				if (upRow == downRow) {
					lowRow = Math.min(upRow, r);
					midRow = Math.max(upRow, r);
				} else if (downRow < upRow) {
					if (r < downRow) {
						lowRow = r;
						midRow = downRow;
						highRow = upRow;
					} else {
						lowRow = downRow;
						midRow = upRow;
						highRow = r;
					}
				}

				if (m[lowRow][c - 1] <= m[midRow][c - 1] && m[lowRow][c - 1] <= m[highRow][c - 1])
					r = lowRow;
				else if (m[midRow][c - 1] <= m[highRow][c - 1] && m[midRow][c - 1] <= m[lowRow][c - 1])
					r = midRow;
				else
					r = highRow;

				System.out.print(" " + (r + 1));
				c--;
			}

			System.out.println();
			System.out.println(minValue);
		}
	}
}

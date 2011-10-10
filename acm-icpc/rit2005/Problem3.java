import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Parse input.
		int width = scan.nextInt() + 1;
		int height = scan.nextInt() + 1;
		int startX = scan.nextInt();
		int startY = scan.nextInt();
		int endX = scan.nextInt();
		int endY = scan.nextInt();
		int numLines = scan.nextInt();

		int[][] boxes = new int[scan.nextInt()][4];
		boolean[][] pointInBox = new boolean[width][height];

		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++)
				boxes[i][j] = scan.nextInt();

			for (int x = boxes[i][0]; x <= boxes[i][2]; x++)
				for (int y = boxes[i][1]; y <= boxes[i][3]; y++)
					pointInBox[x][y] = true;
		}

		// m[x1][y1][x2][y2][d] = true if it is possible to get from (x1, y1) to (x2, y2) in
		// d steps.
		boolean m[][][][][] = new boolean[width][height][width][height][numLines + 1];

		// Set m[x1][y1][x2][y2][1] to true for all pairs of (x1, y1) and (x2, y2)
		// that can "see" each other.
		for (int p1 = 0; p1 < width * height; p1++) {
			int x1 = p1 % width;
			int y1 = p1 / width;

			if (pointInBox[x1][y1])
				continue;

			for (int p2 = p1 + 1; p2 < width * height; p2++) {
				int x2 = p2 % width;
				int y2 = p2 / width;

				if (pointInBox[x2][y2])
					continue;

				if (!segmentIntersectsBox(x1, y1, x2, y2, boxes)) {
					m[x1][y1][x2][y2][1] = true;
					m[x2][y2][x1][y1][1] = true;
				}
			}
		}

		// Fill in table.
		for (int d = 2; d <= numLines; d++) {
			for (int p1 = 0; p1 < width * height; p1++) {
				int x1 = p1 % width;
				int y1 = p1 / width;

				if (pointInBox[x1][y1])
					continue;

				for (int p2 = p1 + 1; p2 < width * height; p2++) {
					int x2 = p2 % width;
					int y2 = p2 / width;

					if (pointInBox[x2][y2])
						continue;

					// Try to find a midpoint (x3, y3) such that we can get from
					// (x1, y1) to (x3, y3) in i steps and from (x3, y3) to (x2, y2)
					// in d-i steps.  If we can find such a point, clearly we can
					// get to (x1, y1) to (x2, y2) in d steps.
findIntermediatePoint:
					for (int x3 = 0; x3 < width; x3++) {
						for (int y3 = 0; y3 < height; y3++) {
							if (pointInBox[x3][y3])
								continue;

							for (int i = 1; i < d; i++) {
								if (m[x1][y1][x3][y3][d - i] && m[x2][y2][x3][y3][i]) {
									m[x1][y1][x2][y2][d] = true;
									m[x2][y2][x1][y1][d] = true;
									break findIntermediatePoint;
								}
							}
						}
					}
				}
			}
		}

		if (m[startX][startY][endX][endY][numLines])
			printSolution(m, startX, startY, endX, endY, numLines);
		else
			System.out.println("impossible");
	}


	/*
	 * Recursively goes through dynamic programming table to rebuild solution.
	 */
	public static void printSolution(boolean[][][][][] m, int x1, int y1, int x2, int y2, int d) {
		if (d == 1)
			return;

		for (int x3 = 0; x3 < m.length; x3++)
			for (int y3 = 0; y3 < m[x3].length; y3++)
				for (int i = 1; i < d; i++)
					if (m[x1][y1][x3][y3][d - i] && m[x2][y2][x3][y3][i]) {
						// If we find a 'midpoint', recursively break down both sides.
						printSolution(m, x1, y1, x3, y3, d - i);
						System.out.println(x3 + " " + y3);
						printSolution(m, x3, y3, x2, y2, i);
						return;
					}
	}


	public static boolean segmentIntersectsBox(int x1, int y1, int x2, int y2, int[][] boxes) {
		// Loop through boxes and treat box edges as line segments.
		for (int[] box : boxes)
			if (segmentsIntersect(x1, y1, x2, y2, box[0], box[1], box[2], box[1]) ||
					segmentsIntersect(x1, y1, x2, y2, box[0], box[1], box[0], box[3]) ||
					segmentsIntersect(x1, y1, x2, y2, box[0], box[3], box[2], box[3]) ||
					segmentsIntersect(x1, y1, x2, y2, box[2], box[1], box[2], box[3]))
				return true;
		return false;
	}


	/*
	 * We can paramaterize a line by L = P1 + m(P2 - P1).  If we let m be in the
	 * interval [0, 1], we paramaterize the line segment from P1 to P2.  Therefore,
	 * we can solve the equation to determine where the segments intersect.
	 * 
	 *                       P1 + ma(P2 - P1) = P3 + mb(P4 - P3)
	 *
	 * We can determine if the segments intersect by determining if ma and mb are
	 * in the range [0, 1].
	 */
	public static boolean segmentsIntersect(int x1, int y1, int x2, int y2,
			int x3, int y3, int x4, int y4) {

		int denom = (y4 - y3)*(x2 - x1) - (x4 - x3)*(y2 - y1);
		int numa = (x4 - x3)*(y1 - y3) - (y4 - y3)*(x1 - x3);
		int numb = (x2 - x1)*(y1 - y3) - (y2 - y1)*(x1 - x3);

		if (numa == 0 && numb == 0 && denom == 0) {
			// Lines are the same.
			return true;
		} else if (denom == 0) {
			// Lines are parallel.
			return false;
		}

		double ma = (double)numa / denom;
		double mb = (double)numb / denom;

		final double EPS = 0.000001;
		return ma >= -EPS && ma <= 1 + EPS && mb >= -EPS && mb <= 1 + EPS;
	}
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Longest Paths.
public class Problem10000 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		for (int k = 0; ; k++) {
			int n = scan.nextInt();
			if (n == 0)
				break;

			ArrayList<LinkedList<Integer>> edges = new ArrayList<LinkedList<Integer>>();
			for (int i = 0; i < n; i++)
				edges.add(new LinkedList<Integer>());

			int s = scan.nextInt() - 1;

			while (true) {
				int v = scan.nextInt() - 1;
				int u = scan.nextInt() - 1;

				if (u == s) continue;
				if (v == -1) break;

				edges.get(v).add(u);
			}

			int dist = 0;
			int fin = s;

			int[] max = new int[n];

			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(s);
			while (q.size() > 0) {
				int v = q.poll();

				if (dist < max[v]) {
					dist = max[v];
					fin = v;
				} else if (dist == max[v] && v < fin)
					fin = v;

				for (int u : edges.get(v))
					if (max[u] < max[v] + 1) {
						max[u] = max[v] + 1;
						q.offer(u);
					}
			}

			System.out.println("Case " + (k + 1) + ": The longest path from "
					+ (s + 1) + " has length " + dist + ", finishing at " + (fin + 1) + ".");
			System.out.println();
		}
	}
}

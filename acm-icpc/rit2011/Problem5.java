import java.util.*;

public class Problem5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        List<HashSet<Integer>> adjList = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < n; i++)
            adjList.add(new HashSet<Integer>());

        int m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt() - 1;
            int v = scan.nextInt() - 1;

            adjList.get(v).add(u);
            adjList.get(u).add(v);
        }

        int k = scan.nextInt();
        for (int i = 0; i < k; i++) {
            int u = scan.nextInt() - 1;
            int v = scan.nextInt() - 1;

            boolean p = oddPath(adjList, u, v);
            System.out.println(p ? "YES" : "NO");
        }
    }

    public static boolean oddPath(List<HashSet<Integer>> adjList, int s, int t) {
    }
}

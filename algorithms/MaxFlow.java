import java.util.*;

public class MaxFlow {
    public static int edmondsKarp(WeightedDigraph g, int s, int t) {
        WeightedDigraph r = new WeightedDigraph(g);

        while (true) {
            // Find augmenting path with BFS.

            // Store capacity for this path.
            int[] maxCapacity = new int[g.vertexCount()];
            maxCapacity[s] = Integer.MAX_VALUE;

            // Remember path.
            int[] parent = new int[g.vertexCount()];
            Arrays.fill(parent, -1);
            parent[s] = s;

            // BFS.
            Queue<Integer> q = new LinkedList<Integer>();
            q.offer(s);
            while (!q.isEmpty()) {
                int u = q.poll();

                for (int v : r.getNeighbors(u)) {
                    // Check residual graph for capacity remaining.
                    int capacityLeft = r.getEdgeWeight(u, v);
                    if (capacityLeft > 0 && parent[v] == -1) {
                        parent[v] = u;
                        maxCapacity[v] = Math.min(maxCapacity[u], capacityLeft);

                        if (v != t)
                            q.offer(v);
                        else {
                            // If we made it to t, update the risudual graph.
                            while (parent[v] != v) {
                                int w = parent[v];
                                if (!r.hasEdge(v, w))
                                    r.addEdge(v, w, 0);

                                r.setEdgeWeight(w, v, r.getEdgeWeight(w, v) - maxCapacity[t]);
                                r.setEdgeWeight(v, w, r.getEdgeWeight(v, w) + maxCapacity[t]);
                                v = w;
                            }
                            q.clear();
                            break;
                        }
                    }
                }
            }
            if (parent[t] == -1) {
                // Couldn't find an augmenting path?  Then we're done.  Compute flow.
                int sum = 0;
                for (int u : r.getNeighbors(t))
                    sum += r.getEdgeWeight(t, u);
                return sum;
            }
        }
    }


    public static void main(String[] args) {
        WeightedDigraph g = new WeightedDigraph(6);

        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 2);
        g.addEdge(3, 4, 4);
        g.addEdge(3, 5, 2);
        g.addEdge(4, 5, 3);

        int maxFlow = edmondsKarp(g, 0, 5);
        System.out.println(maxFlow);
    }
}

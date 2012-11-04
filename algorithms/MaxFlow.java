import java.util.*;

public class MaxFlow {
    public static int edmondsKarp(WeightedDigraph g, int s, int t) {
        WeightedDigraph r = new WeightedDigraph(g);

        while (pi[t] != -1) {
            // Find augmenting path with BFS.
            // Store capacity for this path.
            int[] maxCapacity = new int[g.vertexCount()];
            maxCapacity[s] = Integer.MAX_VALUE;

            // Remember path.
            int[] pi = new int[g.vertexCount()];
            Arrays.fill(pi, -1);
            pi[s] = s;

            // BFS.
            Queue<Integer> q = new LinkedList<Integer>();
            q.offer(s);
            while (!q.isEmpty()) {
                int u = q.poll();

                for (int v : r.neighbors(u)) {
                    // Check residual graph for capacity remaining.
                    int capacityLeft = r.getEdgeWeight(u, v);
                    if (capacityLeft > 0 && pi[v] == -1) {
                        pi[v] = u;
                        maxCapacity[v] = Math.min(maxCapacity[u], capacityLeft);

                        if (v != t)
                            q.offer(v);
                        else {
                            // If we made it to t, update the risudual graph.
                            while (pi[v] != v) {
                                int w = pi[v];
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
        }

        // Couldn't find an augmenting path?  Then we're done.  Compute flow.
        int sum = 0;
        for (int u : r.neighbors(t)) {
            // If t wasn't truly a sink, subtract off its original out-going edges.
            sum += (r.getEdgeWeight(t, u) - g.getEdgeWeight(t, u));
        }
        return sum;
    }
}

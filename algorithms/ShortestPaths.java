import java.util.*;

public class ShortestPaths {
    static class Vertex implements Comparable<Vertex> {
        int v, cost;

        public Vertex(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        public boolean equals(Object o) {
            return ((Vertex)o).v == v;
        }

        public int compareTo(Vertex p) {
            if (cost == p.cost)
                return v - p.v;
            return cost - p.cost;
        }
    }

    // SSSP positive edge weights
    public static int[][] dijkstra(WeightedGraph g, int s) {
        int[] d = new int[g.vertexCount()]; // dist
        int[] pi = new int[g.vertexCount()]; // previous; only needed to reconstruct path

        for (int v = 0; v < g.vertexCount(); v++) {
            d[v] = Integer.MAX_VALUE;
            pi[v] = -1;
        }

        d[s] = 0;

        TreeSet<Vertex> q = new TreeSet<Vertex>();
        q.add(new Vertex(s, 0));

        while (!q.isEmpty()) {
            Vertex min = q.pollFirst();
            int u = min.v;
            for (int v : g.getNeighbors(min.v)) {
                int newDist = d[u] + g.getEdgeWeight(u, v);
                if (d[v] > newDist) {
                    d[v] = newDist;
                    pi[v] = min.v;

                    Vertex vertex = new Vertex(v, dist[v]);
                    q.remove(vertex);
                    q.add(vertex);
                }
            }
        }

        return new int[][] { dist, previous };
    }

    // SSSP neg. edge weights
    // to check for neg cycle, run this alg, then see if any edges
    // can still be relaxed
    public static int[][] bellmanFord(WeightedGraph g, int s) {
        int[] dist = new int[g.vertexCount()];
        int[] previous = new int[g.vertexCount()];

        for (int v = 0; v < g.vertexCount(); v++) {
            dist[v] = Integer.MAX_VALUE;
            previous[v] = -1;
        }

        dist[s] = 0;

        for (int i = 0; i < g.vertexCount() - 1; i++) {
            for (int v = 0; v < g.vertexCount(); v++) {
                for (int u : g.getNeighbors(v)) {
                    if (dist[u] > dist[v] + g.getEdgeWeight(u, v)) {
                        dist[u] = dist[v] + g.getEdgeWeight(u, v);
                        previous[u] = v;
                    }
                }
            }
        }

        return new int[][] { dist, previous };
    }

    // APSP
    public static int[][] floydWarshall(WeightedGraph g) {
        int[][] dist = new int[g.vertexCount()][g.vertexCount()];

        for (int i = 0; i < g.vertexCount(); i++)
            for (int j = i + 1; j < g.vertexCount(); j++)
                if (g.edgeExists(i, j))
                    dist[i][j] = g.getEdgeWeight(i, j);
                else
                    dist[i][j] = Integer.MAX_VALUE;

        for (int k = 0; k < dist.length; k++)
            for (int i = 0; i < dist.length; i++)
                for (int j = i; j < dist.length; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        return dist;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph(7);
        g.addUndirectedEdge(1, 2, 7);
        g.addUndirectedEdge(1, 3, 9);
        g.addUndirectedEdge(1, 6, 14);
        g.addUndirectedEdge(2, 4, 15);
        g.addUndirectedEdge(3, 4, 11);
        g.addUndirectedEdge(3, 6, 2);
        g.addUndirectedEdge(4, 5, 6);
        g.addUndirectedEdge(5, 6, 9);

        int[][] d = dijkstra(g, 1);
        System.out.println(Arrays.toString(d[0]));
        d = bellmanFord(g, 1);
        System.out.println(Arrays.toString(d[0]));
    }
}

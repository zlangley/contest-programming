import java.util.*;

public class WeightedDigraph {
    int[][] adjMatrix;

    public WeightedDigraph(int n) {
        adjMatrix = new int[n][n];
    }

    public WeightedDigraph(WeightedDigraph g) {
        for (int i = 0; i < g.vertexCount(); i++)
            adjMatrix[i] = g.adjMatrix[i].clone();
    }

    public List<Integer> neighbors(int v) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < adjMatrix.length; i++)
            if (adjMatrix[v][i] != 0)
                neighbors.add(i);
        return neighbors;
    }

    public int getEdgeWeight(int u, int v) {
        return adjMatrix[u][v];
    }

    public void setEdgeWeight(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
    }

    public int vertexCount() {
        return adjMatrix.length;
    }

    public boolean hasEdge(int u, int v) {
        return adjMatrix[u][v] != 0;
    }

    public void addEdge(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
    }
}

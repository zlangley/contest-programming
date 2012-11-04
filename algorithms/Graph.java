import java.util.*;

public class Graph {
    int[][] adjMatrix;

    public Graph(int n) {
        adjMatrix = new int[n][n];
    }

    public Graph(Graph g) {
        adjMatrix = new int[g.vertexCount()][g.vertexCount()];
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

    public int vertexCount() {
        return adjMatrix.length;
    }

    public boolean hasEdge(int u, int v) {
        return adjMatrix[u][v] != 0;
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }
}

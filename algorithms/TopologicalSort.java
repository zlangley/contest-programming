import java.util.*;

public class TopologicalSort {
    public static List<Integer> topologicalSort(Graph g) {
        List<Integer> sortedVertices = new ArrayList<Integer>();
        boolean[] visited = new boolean[g.vertexCount()];

        for (int i = 0; i < g.vertexCount(); i++)
            topologicalVisit(g, i, sortedVertices, visited);

        return sortedVertices;
    }

    public static void topologicalVisit(Graph g, int root, List<Integer> sorted, boolean[] visited) {
        if (!visited[root]) {
            visited[root] = true;
            for (int v : g.verticesAdjacentTo(root))
                topologicalVisit(g, v, sorted, visited);

            sorted.add(0, root);
        }
    }
}

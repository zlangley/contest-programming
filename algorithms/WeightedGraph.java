import java.util.*;

public class WeightedGraph extends WeightedDigraph {
    public WeightedGraph(int n) {
       super(n);
    }

    public WeightedGraph(WeightedGraph g) {
        super(g);
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        super.addEdge(u, v, weight);
        super.addEdge(v, u, weight);
    }
}

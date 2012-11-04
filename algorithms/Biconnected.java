public class Biconnected {
    private int bridges;      // number of bridges
    private int cnt;          // counter
    private int[] pre;        // pre[v] = order in which dfs examines v
    private int[] low;        // low[v] = lowest preorder of any vertex connected to v
    private boolean[] articulation;
    private boolean[][] bridge;

    public Biconnected(Graph G) {
        low = new int[G.vertexCount()];
        pre = new int[G.vertexCount()];
        articulation = new boolean[G.vertexCount()];
        bridge = new boolean[G.vertexCount()][G.vertexCount()];

        for (int v = 0; v < G.vertexCount(); v++)
            low[v] = -1;
        for (int v = 0; v < G.vertexCount(); v++)
            pre[v] = -1;
        
        for (int v = 0; v < G.vertexCount(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

    public int components() { return bridges + 1; }

    private void dfs(Graph g, int u, int v) {
        int children = 0;
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : g.neighbors(v)) {
            if (pre[w] == -1) {
                children++;
                dfs(g, v, w);

                low[v] = Math.min(low[v], low[w]);

                if (low[w] == pre[w]) {
                    bridge[v][w] = true;
                    bridges++;
                }
                if (low[w] >= pre[v] && u != v) {
                    articulation[v] = true;
                }
            }

            // update low number - ignore reverse of edge leading to v
            else if (w != u)
                low[v] = Math.min(low[v], pre[w]);
        }

        // root of DFS is an articulation point if it has more than 1 child
        if (u == v && children > 1)
            articulation[v] = true; 
    }

    public boolean isArticulation(int v) { return articulation[v]; }

    public boolean isBridge(int u, int v) { return bridge[u][v]; }

    public int sameComponent(int u, int v) { return low[u] == low[v]; }
}

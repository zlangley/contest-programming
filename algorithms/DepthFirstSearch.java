public class DepthFirstSearch {
    private int count;
    private int s;
    private int[] edgeTo;
    private boolean[] marked;

    public DepthFirstSearch(Digraph g, int s) {
        marked = new boolean[g.vertexCount()];
        edgeTo = new int[g.vertexCount()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        count++;
        marked[v] = true;
        for (int w : g.neighbors(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public int count() { return count; }
    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}

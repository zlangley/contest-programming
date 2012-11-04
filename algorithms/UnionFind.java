public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (rank[a] < rank[b])
            parent[a] = b;
        else {
            parent[b] = a;
            if (rank[a] == rank[b])
                rank[a]++;
        }
    }
}

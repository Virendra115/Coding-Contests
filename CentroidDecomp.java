static class CentroidDecomp {
    int n;
    List<List<Integer>> g;
    int[] size,parent;
    boolean[] seen;

    CentroidDecomp(int n, List<List<Integer>> g) {
        this.n = n;
        this.g = g;
        size = new int[n];
        parent = new int[n];
        seen = new boolean[n];
        initCentroid(0, -1);
    }

    int getSize(int u, int v) {
        if (seen[u])  return 0;
        size[u] = 1;
        for (int next : g.get(u)) {
            if (next != v) {
                size[u] += getSize(next, u);
            }
        }
        return size[u];
    }

    void initCentroid(int u, int v) {
        getSize(u, v);
        final int c = findCentroid(u, -1, size[u]);
        seen[c] = true;
        parent[c] = v;
        for (int next : g.get(c)) {
            if (!seen[next]) {
                initCentroid(next, c);
            }
        }
    }

    int findCentroid(int u, int v, int currSize) {
        for (int x : g.get(u)) {
            if (x != v) {
                if (!seen[x] && size[x] > currSize / 2) {
                    return findCentroid(x, u, currSize);
                }
            }
        }
        return u;
    }
}

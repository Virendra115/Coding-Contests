class DSU {
    public int[] dsu;
 
    public DSU(int N) {
        dsu = new int[N+1];
        for(int i=0; i <= N; i++) {
            dsu[i] = i;
        }
    }
    //with path compression, no find by rank
    public int find(int x) {
        return dsu[x] == x ? x : (dsu[x] = find(dsu[x]));
    }
    public void merge(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        dsu[fx] = fy;
    }
}

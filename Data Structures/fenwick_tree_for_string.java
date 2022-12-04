class FenwickTree
{
    public int[][] tree;
    public int size;
 
    public FenwickTree(int size)
    {
        this.size = size;
        tree = new int[size+5][26];
    }
    public void add(int i,int c, int v)
    {
        while(i <= size)
        {
            tree[i][c] += v;
            i += i&-i;
        }
    }
    public int[] find(int i)
    {
        int[] res = new int[26];
        while(i >= 1)
        {
            for(int j=0;j<26;j++) res[j] += tree[i][j];
            i -= i&-i;
        }
        return res;
    }
    public int[] find(int l, int r)
    {
        int[] right = find(r);
        int[] left = find(l-1);
        for(int j=0;j<26;j++) right[j] -= left[j];
        return right;
    }
}

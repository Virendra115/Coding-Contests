static class SegTree {
    long[] arr, tree;
    int n;

    public SegTree(long[] arr) {
        n = arr.length;
        this.arr = Arrays.copyOf(arr, n);
        tree = new long[4 * n];
        buildSeg(0, 0, n - 1);
    }

    long work(long a, long b) {
        // change as required
        return Math.min(a, b);
    }

    long q(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }
    void u(int value, int pos) {
        update(0, 0, n - 1, value, pos);
    }

    void buildSeg(int ind, int low, int high) {
        if (low == high) {
            tree[ind] = arr[low];
            return;
        }
        int mid = (low + high) / 2;
        buildSeg(2 * ind + 1, low, mid);
        buildSeg(2 * ind + 2, mid + 1, high);
        tree[ind] = work(tree[2 * ind + 1], tree[2 * ind + 2]);
    }

    long query(int ind, int low, int high, int l, int r) {
        if (l <= low && high <= r)
            return tree[ind];
        if (r < low || high < l)
            return Integer.MAX_VALUE;
        int mid = (low + high) / 2;
        long left = query(2 * ind + 1, low, mid, l, r);
        long right = query(2 * ind + 2, mid + 1, high, l, r);
        return work(left, right);
    }

    void update(int ind, int low, int high, int k, int u) {
        if (low == high) {
            tree[ind] = u;
            return;
        }
        int mid = (low + high) / 2;
        if (k <= mid)
            update(2 * ind + 1, low, mid, k, u);
        else
            update(2 * ind + 2, mid + 1, high, k, u);
        tree[ind] = work(tree[2 * ind + 1], tree[2 * ind + 2]);
    }

    void segPrint() {
        print(Arrays.toString(tree));
    }
}

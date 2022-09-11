static class SegTree {
    long[] arr, tree;
    int n,change;

    public SegTree(long[] arr) {
        n = arr.length;
        change = ???????;
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
    void u(int pos, int value) {
        update(0, 0, n - 1, pos, value);
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
            return change;
        int mid = (low + high) / 2;
        long left = query(2 * ind + 1, low, mid, l, r);
        long right = query(2 * ind + 2, mid + 1, high, l, r);
        return work(left, right);
    }

    void update(int ind, int low, int high, int i, int val) {
        if (low == high) {
            tree[ind] = val;
            return;
        }
        int mid = (low + high) / 2;
        if (i <= mid)
            update(2 * ind + 1, low, mid, i, val);
        else
            update(2 * ind + 2, mid + 1, high, i, val);
        tree[ind] = work(tree[2 * ind + 1], tree[2 * ind + 2]);
    }

    void segPrint() {
        print(Arrays.toString(tree));
    }
}

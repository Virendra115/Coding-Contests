class SegmentTreeSet {
    int start;
    int n;
    int[] c;

    // [low, high] inclusive
    public SegmentTreeSet(int low, int high) {
        start = low;
        int len = high - low + 1;
        n = 1;
        while (n < len) n <<= 1;
        c = new int[n * 2];
    }

    private void set(int index, int value) {
        index -= start;
        for (c[index += n] = value; (index >>= 1) > 0; ) {
            c[index] = c[index << 1] + c[index << 1 | 1];
        }
    }

    public int count(int key) {
        return c[key - start + n];
    }

    public void add(int key) {
        set(key, count(key) + 1);
    }

    public void remove(int key) {
        set(key, count(key) - 1);
    }

    public int size() {
        return c[1];
    }

    // index = 0 ... n - 1
    public int get(int index) {
        int cur = 1;
        while (cur < n) {
            if (c[cur << 1] >= index + 1) {
                cur = cur << 1;
            } else {
                index -= c[cur << 1];
                cur = cur << 1 | 1;
            }
        }
        return cur - n + start;
    }

    public int first() {
        int cur = 1;
        while (cur < n) {
            if (c[cur << 1] > 0) {
                cur = cur << 1;
            } else {
                cur = cur << 1 | 1;
            }
        }
        return cur - n + start;
    }

    public int last() {
        int cur = 1;
        while (cur < n) {
            if (c[cur << 1 | 1] > 0) {
                cur = cur << 1 | 1;
            } else {
                cur = cur << 1;
            }
        }
        return cur - n + start;
    }

    public int lowerCount(int key) {
        key = key - 1 - start;
        if (key < 0) return 0;
        int sum = 0;
        int cur = 1;
        int l = 0, r = n - 1;
        while (cur < n) {
            if (key > ((l + r) >> 1)) {
                sum += c[cur << 1];
                cur = cur << 1 | 1;
                l = ((l + r) >> 1) + 1;
            } else {
                cur = cur << 1;
                r = ((l + r) >> 1);
            }
        }
        sum += c[cur];
        return sum;
    }

    public int higherCount(int key) {
        return size() - lowerCount(key) - count(key);
    }

    public boolean contains(int key) {
        return count(key) > 0;
    }

    public Integer ceiling(int key) {
        int id = lowerCount(key);
        return id >= size() ? null : get(id);
    }

    public Integer floor(int key) {
        int id = size() - higherCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public Integer lower(int key) {
        int id = lowerCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public Integer higher(int key) {
        int id = size() - higherCount(key);
        return id >= size() ? null : get(id);
    }

    // return the first index whose value >= target
    // if this value doesn't exist, return index = size()
    public int lowerBound(int target) {
        return lowerCount(target);
    }

    // return the first index whose value > target
    // if this value doesn't exist, return index = size()
    public int upperBound(int target) {
        return size() - higherCount(target);
    }


    public int index(int key) {
        return lowerCount(key);
    }
}
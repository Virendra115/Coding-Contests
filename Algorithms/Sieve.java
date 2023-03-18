static List<Integer> prime = new ArrayList<>();

static void sieve(int n) {
    boolean[] f = new boolean[n+1];
    Arrays.fill(f, true);
    for (int i = 2; i <= n; i++) {
        if (f[i]) {
            prime.add(i);
            for (int j = 2*i; j <= n; j+=i) {
                f[j] = false;
            }
        }
    }
}

static long binpow(long a, long b, long mod) {
    if (b == 0)
        return 1;
    long res = binpow(a, b / 2);
    res = res * res % mod;
    if (b % 2 == 1)
        return res * a % mod;
    else
        return res;
}

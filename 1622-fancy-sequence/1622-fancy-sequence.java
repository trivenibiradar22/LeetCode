import java.util.*;

class Fancy {

    static final long MOD = 1000000007;

    List<Long> seq;
    long mul = 1;
    long add = 0;

    public Fancy() {
        seq = new ArrayList<>();
    }

    public void append(int val) {
        long x = (val - add + MOD) % MOD;
        x = x * modInverse(mul) % MOD;

        seq.add(x);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = mul * m % MOD;
        add = add * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= seq.size()) {
            return -1;
        }

        long ans = seq.get(idx) * mul % MOD;
        ans = (ans + add) % MOD;

        return (int) ans;
    }
    private long modInverse(long x) {
        return power(x, MOD - 2);
    }

    private long power(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b /= 2;
        }

        return result;
    }
}
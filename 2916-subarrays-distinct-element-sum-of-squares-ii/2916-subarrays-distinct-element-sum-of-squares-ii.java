import java.util.Arrays;

class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long mod = 1_000_000_007;
        SegmentTree tree = new SegmentTree(n);
        int[] last = new int[100005];
        Arrays.fill(last, -1);
        long totalSum = 0;
        
        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            tree.update(prev + 1, i, 1);
            last[nums[i]] = i;
            totalSum = (totalSum + tree.query()) % mod;
        }
        
        return (int) totalSum;
    }

    static class SegmentTree {
        int size;
        long[] sum;
        long[] count;
        long[] lz;
        long MOD = 1_000_000_007;

        SegmentTree(int n) {
            size = n;
            sum = new long[4 * n];
            count = new long[4 * n];
            lz = new long[4 * n];
        }

        void update(int L, int R, int node, int l, int r, long val) {
            if (L > r || R < l) return;
            if (L <= l && r <= R) {
                sum[node] = (sum[node] + 2 * count[node] * val + val * val * (r - l + 1)) % MOD;
                count[node] = (count[node] + val * (r - l + 1)) % MOD;
                lz[node] = (lz[node] + val) % MOD;
                return;
            }
            int mid = (l + r) / 2;
            pushDown(node, l, mid, r);
            update(L, R, 2 * node, l, mid, val);
            update(L, R, 2 * node + 1, mid + 1, r, val);
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            count[node] = (count[2 * node] + count[2 * node + 1]) % MOD;
        }

        void pushDown(int node, int l, int mid, int r) {
            if (lz[node] != 0) {
                long v = lz[node];
                int leftChild = 2 * node;
                int rightChild = 2 * node + 1;
                
                sum[leftChild] = (sum[leftChild] + 2 * count[leftChild] * v + v * v * (mid - l + 1)) % MOD;
                count[leftChild] = (count[leftChild] + v * (mid - l + 1)) % MOD;
                lz[leftChild] = (lz[leftChild] + v) % MOD;

                sum[rightChild] = (sum[rightChild] + 2 * count[rightChild] * v + v * v * (r - mid)) % MOD;
                count[rightChild] = (count[rightChild] + v * (r - mid)) % MOD;
                lz[rightChild] = (lz[rightChild] + v) % MOD;

                lz[node] = 0;
            }
        }

        void update(int L, int R, long val) {
            update(L, R, 1, 0, size - 1, val);
        }

        long query() {
            return sum[1];
        }
    }
}
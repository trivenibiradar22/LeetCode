import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        Deque<long[]> dq = new ArrayDeque<>();
        long S = 0;
        int r = n - 1;
        long ans = 0;

        for (int l = n - 1; l >= 0; l--) {
            long x = nums[l];
            long end = l;

            while (!dq.isEmpty() && dq.peekFirst()[0] <= x) {
                long[] t = dq.pollFirst();
                S -= t[0] * (t[2] - t[1] + 1);
                end = t[2];
            }
            dq.addFirst(new long[]{x, l, end});
            S += x * (end - l + 1);

            while (S - (pre[r + 1] - pre[l]) > k) {
                long[] seg = dq.peekLast();
                S -= seg[0];
                seg[2]--;
                if (seg[2] < seg[1]) dq.pollLast();
                r--;
            }

            ans += r - l + 1;
        }

        return ans;
    }
}
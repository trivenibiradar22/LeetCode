
import java.util.*;

class Solution {

    static class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        long leftSum = 0, rightSum = 0;
        int leftSize = 0, rightSize = 0;

        void add(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void remove(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            while (leftSize > rightSize + 1) {
                int v = left.lastKey();
                remove(left, v);
                leftSize--;
                leftSum -= v;

                add(right, v);
                rightSize++;
                rightSum += v;
            }

            while (leftSize < rightSize) {
                int v = right.firstKey();
                remove(right, v);
                rightSize--;
                rightSum -= v;

                add(left, v);
                leftSize++;
                leftSum += v;
            }
        }

        void insert(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                add(left, val);
                leftSize++;
                leftSum += val;
            } else {
                add(right, val);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void erase(int val) {
            if (left.containsKey(val)) {
                remove(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                remove(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        long getCost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                    + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < n; i++) {
            window.insert(nums[i]);

            if (i >= x) {
                window.erase(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}

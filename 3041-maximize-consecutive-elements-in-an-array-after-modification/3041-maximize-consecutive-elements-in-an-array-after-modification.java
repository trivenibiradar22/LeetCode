import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[1_000_003];
        int ans = 0;

        for (int x : nums) {
            // Use x as x + 1 (must read dp[x] before this element updates it)
            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);
            // Use x as x
            dp[x] = Math.max(dp[x], dp[x - 1] + 1);

            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }
        return ans;
    }
}
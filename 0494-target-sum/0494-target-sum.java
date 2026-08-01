class Solution {

    Integer[][] dp;

    public int findTargetSumWays(int[] nums, int target) {

        dp = new Integer[nums.length][2001];

        return dfs(nums, 0, 0, target);
    }

    private int dfs(int[] nums, int index, int sum, int target) {

        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        if (dp[index][sum + 1000] != null) {
            return dp[index][sum + 1000];
        }

        int add = dfs(nums, index + 1, sum + nums[index], target);

        int subtract = dfs(nums, index + 1, sum - nums[index], target);

        return dp[index][sum + 1000] = add + subtract;
    }
}
class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        long[] dp = new long[k];
        
        for (int i = 0; i < n; i++) {
            long[] nextDp = new long[k];
            int rem = (int)(nums[i] % k);
            nextDp[rem]++;
            
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int)((long)r * rem % k);
                    nextDp[newRem] += dp[r];
                }
            }
            
            for (int r = 0; r < k; r++) {
                dp[r] = nextDp[r];
                result[r] += dp[r];
            }
        }
        
        return result;
    }
}
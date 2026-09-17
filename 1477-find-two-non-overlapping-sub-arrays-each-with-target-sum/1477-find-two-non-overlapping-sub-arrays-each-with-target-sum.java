class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n];
        int minLen = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        
        int sum = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target) {
                sum -= arr[left++];
            }
            
            if (sum == target) {
                int currLen = right - left + 1;
                
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currLen + dp[left - 1]);
                }
                
                minLen = Math.min(minLen, currLen);
            }
            
            dp[right] = minLen;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squared[i] = val * val;
        }
        java.util.Arrays.sort(squared);
        long ans = 0;
        int mid = n / 2;
        for (int i = 0; i < mid; i++) {
            ans -= squared[i];
        }
        for (int i = mid; i < n; i++) {
            ans += squared[i];
        }
        return ans;
    }
}
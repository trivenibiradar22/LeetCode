class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];
        
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            
            int minVal = Math.min(a, b);
            int maxVal = Math.max(a, b);
            
            int sum2 = 2;
            int sumMin = 1 + minVal;
            int sumMax = limit + maxVal;
            int sum2Limit = 2 * limit;
            
            diff[sum2] += 2;
            diff[sumMin] -= 1;
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
            diff[sumMax + 1] += 1;
            if (sum2Limit + 1 < diff.length) {
                diff[sum2Limit + 1] += 1;
            }
        }
        
        int res = n;
        int currentMoves = 0;
        for (int s = 2; s <= 2 * limit; s++) {
            currentMoves += diff[s];
            res = Math.min(res, currentMoves);
        }
        
        return res;
    }
}
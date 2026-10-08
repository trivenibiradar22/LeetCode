class Solution {
    public boolean isGood(int[] nums) {
        int n = 0;
        for (int x : nums) {
            n = Math.max(n, x);
        }
        
        if (nums.length != n + 1) {
            return false;
        }
        
        int[] count = new int[n + 1];
        for (int x : nums) {
            if (x > n) {
                return false;
            }
            count[x]++;
        }
        
        for (int i = 1; i < n; i++) {
            if (count[i] != 1) {
                return false;
            }
        }
        
        return count[n] == 2;
    }
}
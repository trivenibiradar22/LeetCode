class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        if (isAscRotation(nums, n)) {
            int k = indexOfZero(nums, n);
            ans = Math.min(ans, Math.min(k, n - k + 2));
        }

        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }

        if (isAscRotation(rev, n)) {
            int k = indexOfZero(rev, n);
            ans = Math.min(ans, 1 + Math.min(k, n - k));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private boolean isAscRotation(int[] a, int n) {
        int s = a[0];
        for (int i = 0; i < n; i++) {
            if (a[i] != (s + i) % n) {
                return false;
            }
        }
        return true;
    }

    private int indexOfZero(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
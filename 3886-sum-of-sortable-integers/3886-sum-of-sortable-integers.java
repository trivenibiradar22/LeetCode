class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int previous = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int drops = 0;
                int dropIndex = -1;

                for (int j = start; j < start + k; j++) {
                    int next = start + (j - start + 1) % k;

                    if (nums[j] > nums[next]) {
                        drops++;
                        dropIndex = j;
                    }
                }

                if (drops != 1) {
                    if (drops != 0) {
                        valid = false;
                        break;
                    }

                    if (nums[start] < previous) {
                        valid = false;
                        break;
                    }

                    previous = nums[start + k - 1];
                } else {
                    int first = nums[(dropIndex + 1 - start) % k + start];
                    int last = nums[dropIndex];

                    if (first < previous) {
                        valid = false;
                        break;
                    }

                    previous = last;
                }
            }

            if (valid && (k != 1 || isSorted(nums))) {
                ans += k;
            }
        }

        return ans;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
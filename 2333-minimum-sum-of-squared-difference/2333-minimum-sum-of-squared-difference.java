class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        int[] count = new int[maxDiff + 1];
        long totalDiffSum = 0;
        for (int d : diffs) {
            count[d]++;
            totalDiffSum += d;
        }
        
        if (totalDiffSum <= totalK) {
            return 0;
        }
        
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            if (count[d] == 0) continue;
            
            long take = Math.min(totalK, (long) count[d]);
            
            count[d] -= take;
            count[d - 1] += (int) take;
            totalK -= take;
        }
        
        long minSumSq = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                minSumSq += (long) count[d] * d * d;
            }
        }
        
        return minSumSq;
    }
}
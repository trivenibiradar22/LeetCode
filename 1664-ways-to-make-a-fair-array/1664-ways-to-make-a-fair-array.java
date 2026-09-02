class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int totalEvenSum = 0;
        int totalOddSum = 0;
        
        // Calculate total sums for even and odd indices
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalEvenSum += nums[i];
            } else {
                totalOddSum += nums[i];
            }
        }
        
        int fairCount = 0;
        int leftEvenSum = 0;
        int leftOddSum = 0;
        
        for (int i = 0; i < n; i++) {
            // When nums[i] is removed, its parity changes for all elements to its right.
            // Right sums can be derived by subtracting left sums and the current element.
            int rightEvenSum, rightOddSum;
            
            if (i % 2 == 0) {
                rightEvenSum = totalEvenSum - leftEvenSum - nums[i];
                rightOddSum = totalOddSum - leftOddSum;
                
                // After removal, elements to the right switch parities
                if (leftEvenSum + rightOddSum == leftOddSum + rightEvenSum) {
                    fairCount++;
                }
                leftEvenSum += nums[i];
            } else {
                rightEvenSum = totalEvenSum - leftEvenSum;
                rightOddSum = totalOddSum - leftOddSum - nums[i];
                
                // After removal, elements to the right switch parities
                if (leftEvenSum + rightOddSum == leftOddSum + rightEvenSum) {
                    fairCount++;
                }
                leftOddSum += nums[i];
            }
        }
        
        return fairCount;
    }
}
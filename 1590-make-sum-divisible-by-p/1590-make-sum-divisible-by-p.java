import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubarray(int[] nums, int p) {
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int targetRem = (int) (totalSum % p);
        if (targetRem == 0) {
            return 0; // Already divisible by p
        }
        
        // Map to store the last seen index of a specific prefix sum remainder
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        
        long currentSum = 0;
        int minLen = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            int currentRem = (int) (currentSum % p);
            
            // We want (currentRem - targetRem) % p
            int neededRem = (currentRem - targetRem + p) % p;
            
            if (remainderMap.containsKey(neededRem)) {
                minLen = Math.min(minLen, i - remainderMap.get(neededRem));
            }
            
            remainderMap.put(currentRem, i);
        }
        
        return minLen == nums.length ? -1 : minLen;
    }
}
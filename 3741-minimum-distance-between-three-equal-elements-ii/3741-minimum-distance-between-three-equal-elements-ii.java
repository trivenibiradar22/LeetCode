import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        for (List<Integer> indices : map.values()) {
            if (indices.size() >= 3) {
                for (int i = 0; i <= indices.size() - 3; i++) {
                    int p = indices.get(i);
                    int r = indices.get(i + 2);
                    int dist = 2 * (r - p);
                    minDistance = Math.min(minDistance, dist);
                }
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
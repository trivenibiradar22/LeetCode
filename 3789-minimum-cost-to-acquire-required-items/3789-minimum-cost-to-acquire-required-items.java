class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = Math.min((long) cost1, (long) costBoth);
        long c2 = Math.min((long) cost2, (long) costBoth);
        long totalCost = 0;
        
        long common = Math.min(need1, need2);
        totalCost += common * Math.min((long) costBoth, (long) cost1 + (long) cost2);
        
        need1 -= common;
        need2 -= common;
        
        if (need1 > 0) {
            totalCost += need1 * Math.min((long) cost1, (long) costBoth);
        }
        
        if (need2 > 0) {
            totalCost += need2 * Math.min((long) cost2, (long) costBoth);
        }
        
        return totalCost;
    }
}
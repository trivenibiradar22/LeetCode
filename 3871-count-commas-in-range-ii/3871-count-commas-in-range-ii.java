class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long lower = 1000;
        long commas = 1;
        
        while (lower <= n) {
            long upper = (lower * 1000) - 1;
            long countInThisRange = Math.min(n, upper) - lower + 1;
            totalCommas += countInThisRange * commas;
            
            lower *= 1000;
            commas++;
        }
        
        return totalCommas;
    }
}
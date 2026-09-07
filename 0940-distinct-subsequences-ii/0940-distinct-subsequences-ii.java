class Solution {
    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        long total = 0;
        long mod = 1000000007;
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            long newTotal = (total - last[index] + 1 + mod) % mod;
            last[index] = (total + 1) % mod;
            total = (total + newTotal) % mod;
        }
        
        return (int) total;
    }
}
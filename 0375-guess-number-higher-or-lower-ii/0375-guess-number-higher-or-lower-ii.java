class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 2];
        for (int length = 2; length <= n; length++) {
            for (int start = 1; start <= n - length + 1; start++) {
                int end = start + length - 1;
                int minCost = Integer.MAX_VALUE;
                for (int piv = start; piv <= end; piv++) {
                    int cost = piv + Math.max(
                        piv - 1 >= start ? dp[start][piv - 1] : 0,
                        piv + 1 <= end ? dp[piv + 1][end] : 0
                    );
                    minCost = Math.min(minCost, cost);
                }
                dp[start][end] = minCost;
            }
        }
        return dp[1][n];
    }
}
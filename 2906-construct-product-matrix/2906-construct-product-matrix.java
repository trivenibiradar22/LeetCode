class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int mod = 12345;

        int[][] ans = new int[n][m];
        int prefix = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = prefix;
                prefix = (prefix * (grid[i][j] % mod)) % mod;
            }
        }

        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                ans[i][j] = (ans[i][j] * suffix) % mod;
                suffix = (suffix * (grid[i][j] % mod)) % mod;
            }
        }

        return ans;
    }
}
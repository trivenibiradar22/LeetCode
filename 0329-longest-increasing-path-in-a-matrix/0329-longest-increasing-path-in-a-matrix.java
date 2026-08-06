class Solution {
    private int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, dp, i, j));
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int[][] dp, int row, int col) {
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int max = 1;

        for (int[] d : directions) {
            int nr = row + d[0];
            int nc = col + d[1];

            if (nr >= 0 && nr < m &&
                nc >= 0 && nc < n &&
                matrix[nr][nc] > matrix[row][col]) {

                max = Math.max(max, 1 + dfs(matrix, dp, nr, nc));
            }
        }

        dp[row][col] = max;
        return max;
    }
}
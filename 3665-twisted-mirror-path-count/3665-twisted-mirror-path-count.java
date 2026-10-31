class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1000000007;

        int[][] rightNext = new int[m][n];
        int[][] downNext = new int[m][n];
        boolean[][] rightValid = new boolean[m][n];
        boolean[][] downValid = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r = i, c = j + 1;
                int dir = 0;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r = r + 1;
                        dir = 1;
                    } else {
                        c = c + 1;
                        dir = 0;
                    }
                }
                
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    rightValid[i][j] = false;
                } else {
                    rightValid[i][j] = true;
                    rightNext[i][j] = r * n + c;
                }

                r = i + 1; c = j;
                dir = 1;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r = r + 1;
                        dir = 1;
                    } else {
                        c = c + 1;
                        dir = 0;
                    }
                }
                
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    downValid[i][j] = false;
                } else {
                    downValid[i][j] = true;
                    downNext[i][j] = r * n + c;
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % MOD;
                    } else {
                        if (rightValid[i][j]) {
                            int nextR = rightNext[i][j] / n;
                            int nextC = rightNext[i][j] % n;
                            dp[nextR][nextC] = (dp[nextR][nextC] + dp[i][j]) % MOD;
                        }
                    }
                }

                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % MOD;
                    } else {
                        if (downValid[i][j]) {
                            int nextR = downNext[i][j] / n;
                            int nextC = downNext[i][j] % n;
                            dp[nextR][nextC] = (dp[nextR][nextC] + dp[i][j]) % MOD;
                        }
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
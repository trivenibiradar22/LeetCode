class Solution {

    static final long MOD = 1000000007;

    public int maxProductPath(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long[][] max = new long[m][n];
        long[][] min = new long[m][n];

        max[0][0] = grid[0][0];
        min[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                long value = grid[i][j];

                long maxValue = Long.MIN_VALUE;
                long minValue = Long.MAX_VALUE;

                if (i > 0) {
                    maxValue = Math.max(maxValue, max[i - 1][j] * value);
                    maxValue = Math.max(maxValue, min[i - 1][j] * value);

                    minValue = Math.min(minValue, max[i - 1][j] * value);
                    minValue = Math.min(minValue, min[i - 1][j] * value);
                }

                if (j > 0) {
                    maxValue = Math.max(maxValue, max[i][j - 1] * value);
                    maxValue = Math.max(maxValue, min[i][j - 1] * value);

                    minValue = Math.min(minValue, max[i][j - 1] * value);
                    minValue = Math.min(minValue, min[i][j - 1] * value);
                }

                max[i][j] = maxValue;
                min[i][j] = minValue;
            }
        }

        long result = max[m - 1][n - 1];

        if (result < 0) {
            return -1;
        }

        return (int) (result % MOD);
    }
}
import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                set.add(grid[i][j]);

                for (int d = 1; i + 2 * d < m && j - d >= 0 && j + d < n; d++) {
                    int sum = 0;

                    for (int k = 0; k <= d; k++) {
                        sum += grid[i + k][j - k];
                        sum += grid[i + k][j + k];
                        sum += grid[i + d + k][j - d + k];
                        sum += grid[i + d + k][j + d - k];
                    }

                    sum -= grid[i][j];
                    sum -= grid[i + d][j - d];
                    sum -= grid[i + d][j + d];
                    sum -= grid[i + 2 * d][j];

                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] ans = new int[size];

        int index = 0;
        for (int value : set) {
            ans[index++] = value;
            if (index == 3) {
                break;
            }
        }

        return ans;
    }
}
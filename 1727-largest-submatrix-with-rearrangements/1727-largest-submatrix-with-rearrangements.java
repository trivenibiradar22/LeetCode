import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            int[] arr = height.clone();
            Arrays.sort(arr);

            for (int j = n - 1; j >= 0; j--) {
                int width = n - j;
                ans = Math.max(ans, arr[j] * width);
            }
        }

        return ans;
    }
}
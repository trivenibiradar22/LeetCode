class Solution {

    public boolean findRotation(int[][] mat, int[][] target) {

        for (int rotation = 0; rotation < 4; rotation++) {

            if (same(mat, target)) {
                return true;
            }

            rotate(mat);
        }

        return false;
    }

    boolean same(int[][] mat, int[][] target) {

        int n = mat.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    void rotate(int[][] mat) {

        int n = mat.length;

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // Reverse every row
        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = n - 1;

            while (left < right) {

                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;

                left++;
                right--;
            }
        }
    }
}
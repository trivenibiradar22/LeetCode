import java.util.*;

class Solution {

    int[][] rects;
    int[] prefix;
    Random random;

    public Solution(int[][] rects) {

        this.rects = rects;
        this.random = new Random();

        prefix = new int[rects.length];

        int sum = 0;

        for (int i = 0; i < rects.length; i++) {

            int width = rects[i][2] - rects[i][0] + 1;
            int height = rects[i][3] - rects[i][1] + 1;

            sum += width * height;

            prefix[i] = sum;
        }
    }

    public int[] pick() {

        int target = random.nextInt(prefix[prefix.length - 1]) + 1;

        int index = Arrays.binarySearch(prefix, target);

        if (index < 0) {
            index = -index - 1;
        }

        int[] rect = rects[index];

        int width = rect[2] - rect[0] + 1;
        int height = rect[3] - rect[1] + 1;

        int x = rect[0] + random.nextInt(width);
        int y = rect[1] + random.nextInt(height);

        return new int[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] indexedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }

        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n) {
                if (j > i && indexedNums[j][0] - indexedNums[j - 1][0] > limit) {
                    break;
                }
                j++;
            }

            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            for (int k = i; k < j; k++) {
                indices.add(indexedNums[k][1]);
                values.add(indexedNums[k][0]);
            }

            Collections.sort(indices);

            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = values.get(k);
            }

            i = j;
        }

        return result;
    }
}
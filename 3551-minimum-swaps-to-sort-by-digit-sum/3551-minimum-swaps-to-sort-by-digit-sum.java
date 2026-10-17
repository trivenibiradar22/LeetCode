import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            arr[i][0] = nums[i];
            arr[i][1] = sum;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            indexMap.put(nums[i], i);
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != arr[i][0]) {
                swaps++;

                int currentIndex = indexMap.get(arr[i][0]);
                indexMap.put(nums[i], currentIndex);
                indexMap.put(arr[i][0], i);

                int temp = nums[i];
                nums[i] = nums[currentIndex];
                nums[currentIndex] = temp;
            }
        }

        return swaps;
    }
}
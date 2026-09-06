class Solution {
    public int[] sortArray(int[] nums) {
        countingSort(nums);
        return nums;
    }

    private void countingSort(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }

        int range = max - min + 1;
        int[] count = new int[range];
        for (int num : nums) {
            count[num - min]++;
        }

        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                nums[index++] = i + min;
                count[i]--;
            }
        }
    }
}
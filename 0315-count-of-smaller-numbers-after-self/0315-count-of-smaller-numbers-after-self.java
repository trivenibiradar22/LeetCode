class Solution {
    private int[] count;
    private int[] tempIndex;
    private int[] originalIndex;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        originalIndex = new int[n];
        tempIndex = new int[n];

        for (int i = 0; i < n; i++) {
            originalIndex[i] = i;
        }

        mergeSort(nums, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int rightCounter = 0;

        while (i <= mid && j <= right) {
            if (nums[originalIndex[i]] > nums[originalIndex[j]]) {
                rightCounter++;
                tempIndex[k++] = originalIndex[j++];
            } else {
                count[originalIndex[i]] += rightCounter;
                tempIndex[k++] = originalIndex[i++];
            }
        }

        while (i <= mid) {
            count[originalIndex[i]] += rightCounter;
            tempIndex[k++] = originalIndex[i++];
        }

        while (j <= right) {
            tempIndex[k++] = originalIndex[j++];
        }

        for (int p = left; p <= right; p++) {
            originalIndex[p] = tempIndex[p];
        }
    }
}
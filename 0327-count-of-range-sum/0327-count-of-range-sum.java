class Solution {
    private int count = 0;
    private int lower, upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;

        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        mergeSort(prefix, 0, prefix.length - 1);
        return count;
    }

    private void mergeSort(long[] prefix, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(prefix, left, mid);
        mergeSort(prefix, mid + 1, right);

        int low = mid + 1;
        int high = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (low <= right && prefix[low] - prefix[i] < lower) {
                low++;
            }
            while (high <= right && prefix[high] - prefix[i] <= upper) {
                high++;
            }
            count += high - low;
        }

        merge(prefix, left, mid, right);
    }

    private void merge(long[] prefix, int left, int mid, int right) {
        long[] temp = new long[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (prefix[i] <= prefix[j]) {
                temp[k++] = prefix[i++];
            } else {
                temp[k++] = prefix[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = prefix[i++];
        }

        while (j <= right) {
            temp[k++] = prefix[j++];
        }

        System.arraycopy(temp, 0, prefix, left, temp.length);
    }
}
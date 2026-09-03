class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }

        boolean canAllEven = true;
        for (int x : nums1) {
            if (x % 2 != 0) {
                if (minOdd == Integer.MAX_VALUE || x <= minOdd) {
                    canAllEven = false;
                    break;
                }
            }
        }

        boolean canAllOdd = true;
        for (int x : nums1) {
            if (x % 2 == 0) {
                if (minOdd == Integer.MAX_VALUE || x <= minOdd) {
                    canAllOdd = false;
                    break;
                }
            }
        }

        return canAllEven || canAllOdd;
    }
}
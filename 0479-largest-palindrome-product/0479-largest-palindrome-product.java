class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }

        int upper = (int) Math.pow(10, n) - 1;
        int lower = (int) Math.pow(10, n - 1);

        for (int left = upper; left >= lower; left--) {
            String s = String.valueOf(left);
            String rev = new StringBuilder(s).reverse().toString();

            long palindrome = Long.parseLong(s + rev);

            for (int i = upper; i >= lower; i--) {
                if (palindrome / i > upper) {
                    break;
                }

                if (palindrome % i == 0) {
                    return (int) (palindrome % 1337);
                }
            }
        }

        return 0;
    }
}
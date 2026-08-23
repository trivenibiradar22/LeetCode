class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        // First half
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }

        // Second half
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }

        // If number of '?' is odd, Alice gets the last move
        // and can force the sums to be different.
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        /*
         * Bob wins exactly when:
         *
         * leftSum - rightSum
         *     == 9 * (rightQ - leftQ) / 2
         *
         * Otherwise Alice can force unequal sums.
         */
        return 2 * (leftSum - rightSum)
                != 9 * (rightQ - leftQ);
    }
}
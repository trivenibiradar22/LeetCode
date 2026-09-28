class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isGood(int x) {
        boolean isValidRotated = false;
        while (x > 0) {
            int digit = x % 10;
            if (digit == 3 || digit == 4 || digit == 7) {
                return false; // Invalid digit, cannot be rotated
            }
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                isValidRotated = true; // At least one digit changes to a different valid digit
            }
            x /= 10;
        }
        return isValidRotated;
    }
}
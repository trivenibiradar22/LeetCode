class Solution {
    public int superPow(int a, int[] b) {
        int res = 1;
        a %= 1337;
        for (int digit : b) {
            res = pow(res, 10) * pow(a, digit) % 1337;
        }
        return res;
    }

    private int pow(int base, int exp) {
        int result = 1;
        base %= 1337;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % 1337;
        }
        return result;
    }
}
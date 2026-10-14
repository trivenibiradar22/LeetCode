class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if ((long) num * 9 < sum) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num; i++) {
            int val = Math.min(9, sum);
            val = Math.max(val, sum - (num - 1 - i) * 9);
            sb.append(val);
            sum -= val;
        }

        if (sum > 0) {
            return "";
        }

        return sb.toString();
    }
}
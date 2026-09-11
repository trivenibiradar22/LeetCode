class Solution {
    private int index = 0;

    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        int k = 0;

        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
                index++;
            } else if (c == '[') {
                index++;
                String decoded = decodeString(s);
                while (k > 0) {
                    result.append(decoded);
                    k--;
                }
            } else if (c == ']') {
                index++;
                return result.toString();
            } else {
                result.append(c);
                index++;
            }
        }

        return result.toString();
    }
}
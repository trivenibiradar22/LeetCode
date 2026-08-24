class Solution {
    public String maskPII(String s) {
        if (s.contains("@")) {
            s = s.toLowerCase();

            int at = s.indexOf('@');
            char first = s.charAt(0);
            char last = s.charAt(at - 1);

            return first + "*****" + last + s.substring(at);
        }

        StringBuilder digits = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }

        int country = digits.length() - 10;
        String last4 = digits.substring(digits.length() - 4);

        if (country == 0) {
            return "***-***-" + last4;
        }

        return "+" + "*".repeat(country) + "-***-***-" + last4;
    }
}
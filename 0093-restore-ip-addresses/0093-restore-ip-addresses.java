import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, "", result);
        return result;
    }

    private void backtrack(String s, int startIndex, int dotCount, String currentIp, List<String> result) {
        if (dotCount == 4 && startIndex == s.length()) {
            result.add(currentIp.substring(0, currentIp.length() - 1));
            return;
        }
        if (dotCount == 4 || startIndex == s.length()) {
            return;
        }

        for (int i = startIndex; i < Math.min(startIndex + 3, s.length()); i++) {
            String part = s.substring(startIndex, i + 1);
            if (isValid(part)) {
                backtrack(s, i + 1, dotCount + 1, currentIp + part + ".", result);
            }
        }
    }

    private boolean isValid(String part) {
        if (part.length() > 1 && part.startsWith("0")) {
            return false;
        }
        int val = Integer.parseInt(part);
        return val >= 0 && val <= 255;
    }
}
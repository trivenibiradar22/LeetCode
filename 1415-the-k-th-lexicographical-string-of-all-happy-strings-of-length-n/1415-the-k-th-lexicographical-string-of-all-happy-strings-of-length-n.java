import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getHappyString(int n, int k) {
        List<String> happyStrings = new ArrayList<>();
        backtrack(n, new StringBuilder(), happyStrings);
        if (happyStrings.size() < k) {
            return "";
        }
        return happyStrings.get(k - 1);
    }

    private void backtrack(int n, StringBuilder current, List<String> happyStrings) {
        if (current.length() == n) {
            happyStrings.add(current.toString());
            return;
        }

        for (char c : new char[]{'a', 'b', 'c'}) {
            if (current.length() > 0 && current.charAt(current.length() - 1) == c) {
                continue;
            }
            current.append(c);
            backtrack(n, current, happyStrings);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
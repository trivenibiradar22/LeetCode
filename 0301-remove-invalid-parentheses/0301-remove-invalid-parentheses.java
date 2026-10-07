import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, left, right, 0, 0, new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int leftRem, int rightRem,
                           int leftCount, int rightCount,
                           StringBuilder path, Set<String> result) {

        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(') {
            if (leftRem > 0) {
                backtrack(s, index + 1, leftRem - 1, rightRem,
                          leftCount, rightCount, path, result);
            }

            path.append(c);

            backtrack(s, index + 1, leftRem, rightRem,
                      leftCount + 1, rightCount, path, result);

            path.deleteCharAt(path.length() - 1);

        } else if (c == ')') {
            if (rightRem > 0) {
                backtrack(s, index + 1, leftRem, rightRem - 1,
                          leftCount, rightCount, path, result);
            }

            if (rightCount < leftCount) {
                path.append(c);

                backtrack(s, index + 1, leftRem, rightRem,
                          leftCount, rightCount + 1, path, result);

                path.deleteCharAt(path.length() - 1);
            }

        } else {
            path.append(c);

            backtrack(s, index + 1, leftRem, rightRem,
                      leftCount, rightCount, path, result);

            path.deleteCharAt(path.length() - 1);
        }
    }
}
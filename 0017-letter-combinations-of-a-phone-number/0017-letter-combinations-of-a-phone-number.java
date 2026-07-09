import java.util.*;

class Solution {

    List<String> result = new ArrayList<>();

    String[] map = {
        "",     
        "",     
        "abc",  
        "def",  
        "ghi",  
        "jkl",  
        "mno",
        "pqrs", 
        "tuv",
        "wxyz"  
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return result;

        backtrack(digits, 0, new StringBuilder());
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current) {

        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            current.append(ch);
            backtrack(digits, index + 1, current);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}
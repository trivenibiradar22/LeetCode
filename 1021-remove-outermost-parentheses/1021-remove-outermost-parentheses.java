class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                if (openCount > 0) {
                    result.append(c);
                }
                openCount++;
            } else { 
                openCount--;
                if (openCount > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}
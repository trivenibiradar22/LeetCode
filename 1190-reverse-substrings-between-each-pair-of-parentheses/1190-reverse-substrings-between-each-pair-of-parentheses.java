class Solution {
    public String reverseParentheses(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                java.util.List<Character> temp = new java.util.ArrayList<>();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); 
                }
                for (char ch : temp) {
                    stack.push(ch);
                }
            } else {
                stack.push(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }
        
        return result.toString();
    }
}
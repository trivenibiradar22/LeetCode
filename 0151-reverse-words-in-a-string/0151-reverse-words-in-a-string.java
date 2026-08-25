class Solution {
    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;

        // Skip leading and trailing spaces
        while (left <= right && s.charAt(left) == ' ') left++;
        while (left <= right && s.charAt(right) == ' ') right--;

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            
            if (c != ' ') {
                word.append(c);
            } else if (word.length() > 0) {
                // Prepend the accumulated word to the result
                if (result.length() > 0) {
                    result.insert(0, " ");
                }
                result.insert(0, word);
                word.setLength(0); // reset for the next word
            }
            left++;
        }

        // Don't forget the last word
        if (word.length() > 0) {
            if (result.length() > 0) {
                result.insert(0, " ");
            }
            result.insert(0, word);
        }

        return result.toString();
    }
}
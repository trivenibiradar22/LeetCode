import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = new HashSet<>();
        int i = expression.indexOf('{');
        if (i == -1) {
            set.add(expression);
            List<String> res = new ArrayList<>(set);
            Collections.sort(res);
            return res;
        }
        int j = i, count = 0;
        for (; j < expression.length(); j++) {
            if (expression.charAt(j) == '{') count++;
            else if (expression.charAt(j) == '}') count--;
            if (count == 0) break;
        }
        String before = expression.substring(0, i);
        String middle = expression.substring(i + 1, j);
        String after = expression.substring(j + 1);
        
        List<String> strs = new ArrayList<>();
        int commaCount = 0;
        int start = 0;
        for (int k = 0; k < middle.length(); k++) {
            char c = middle.charAt(k);
            if (c == '{') commaCount++;
            else if (c == '}') commaCount--;
            else if (c == ',' && commaCount == 0) {
                strs.add(middle.substring(start, k));
                start = k + 1;
            }
        }
        strs.add(middle.substring(start));
        
        for (String s : strs) {
            List<String> sub = braceExpansionII(before + s + after);
            set.addAll(sub);
        }
        
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }
}
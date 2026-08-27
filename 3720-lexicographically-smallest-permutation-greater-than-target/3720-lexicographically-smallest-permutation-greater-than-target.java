class Solution {
    public String lexGreaterPermutation(String s, String target) {
        char[] sArr = s.toCharArray();
        java.util.Arrays.sort(sArr);
        int n = s.length();
        int[] count = new int[26];
        for (char c : sArr) {
            count[c - 'a']++;
        }
        
        char[] res = new char[n];
        if (dfs(0, count, s, target, res)) {
            return new String(res);
        }
        return "";
    }

    private boolean dfs(int idx, int[] count, String s, String target, char[] res) {
        int n = s.length();
        if (idx == n) {
            return false;
        }

        int startChar = target.charAt(idx) - 'a';
        for (int c = startChar; c < 26; c++) {
            if (count[c] > 0) {
                count[c]--;
                res[idx] = (char) ('a' + c);
                
                if (c > startChar) {
                    if (fillRemaining(idx + 1, count, res)) {
                        return true;
                    }
                } else {
                    if (dfs(idx + 1, count, s, target, res)) {
                        return true;
                    }
                }
                count[c]++;
            }
        }
        return false;
    }

    private boolean fillRemaining(int idx, int[] count, char[] res) {
        int ptr = idx;
        for (int c = 0; c < 26; c++) {
            while (count[c] > 0) {
                count[c]--;
                res[ptr++] = (char) ('a' + c);
            }
        }
        return true;
    }
}
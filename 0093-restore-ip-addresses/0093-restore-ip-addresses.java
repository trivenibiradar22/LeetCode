class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtrack(s, 0, 0, "", ans);
        return ans;
    }

    private void backtrack(String s, int index, int parts, String path, List<String> ans) {
        if (parts == 4 && index == s.length()) {
            ans.add(path.substring(0, path.length() - 1));
            return;
        }

        if (parts == 4 || index == s.length()) {
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String part = s.substring(index, index + len);

            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }

            int num = Integer.parseInt(part);
            if (num <= 255) {
                backtrack(s, index + len, parts + 1, path + part + ".", ans);
            }
        }
    }
}
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i, len = 0;

            while (j < words.length && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();

            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                while (sb.length() < maxWidth) sb.append(" ");
            } else {
                int totalSpaces = maxWidth - len;
                int spaces = totalSpaces / gaps;
                int extra = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spaces; s++)
                            sb.append(" ");
                        if (extra-- > 0)
                            sb.append(" ");
                    }
                }
            }

            ans.add(sb.toString());
            i = j;
        }

        return ans;
    }
}
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        char[] half = new char[n / 2];

        if (!dfs(0, halfFreq, target, half, middle)) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (char c : half) {
            ans.append(c);
        }

        if (n % 2 == 1) {
            ans.append((char) ('a' + middle));
        }

        for (int i = half.length - 1; i >= 0; i--) {
            ans.append(half[i]);
        }

        String result = ans.toString();

        return result.compareTo(target) > 0 ? result : "";
    }

    private boolean dfs(int pos, int[] freq, String target,
                        char[] half, int middle) {

        if (pos == half.length) {
            StringBuilder candidate = new StringBuilder();

            for (char c : half) {
                candidate.append(c);
            }

            if (target.length() % 2 == 1) {
                candidate.append((char) ('a' + middle));
            }

            for (int i = half.length - 1; i >= 0; i--) {
                candidate.append(half[i]);
            }

            return candidate.toString().compareTo(target) > 0;
        }

        int t = target.charAt(pos) - 'a';

        if (freq[t] > 0) {
            half[pos] = (char) ('a' + t);
            freq[t]--;

            if (dfs(pos + 1, freq, target, half, middle)) {
                return true;
            }

            freq[t]++;
        }

        for (int c = t + 1; c < 26; c++) {
            if (freq[c] > 0) {
                half[pos] = (char) ('a' + c);
                freq[c]--;

                int index = pos + 1;

                for (int x = 0; x < 26; x++) {
                    while (freq[x] > 0) {
                        half[index++] = (char) ('a' + x);
                        freq[x]--;
                    }
                }

                return true;
            }
        }

        return false;
    }
}
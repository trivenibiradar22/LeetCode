class Solution {
    private Set<String> set = new HashSet<>();
    private Map<String, Boolean> memo = new HashMap<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        for (String word : words) {
            set.add(word);
        }

        List<String> ans = new ArrayList<>();

        for (String word : words) {
            set.remove(word);

            if (canForm(word)) {
                ans.add(word);
            }

            set.add(word);
            memo.clear();
        }

        return ans;
    }

    private boolean canForm(String word) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (set.contains(prefix)) {
                if (set.contains(suffix) || canForm(suffix)) {
                    memo.put(word, true);
                    return true;
                }
            }
        }

        memo.put(word, false);
        return false;
    }
}
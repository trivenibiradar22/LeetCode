

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
 class WordDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {

        if (node == null)
            return false;

        if (index == word.length())
            return node.isEnd;

        char ch = word.charAt(index);

        if (ch == '.') {

            for (TrieNode child : node.children) {
                if (dfs(word, index + 1, child))
                    return true;
            }

            return false;
        }

        return dfs(word, index + 1, node.children[ch - 'a']);
    }
}
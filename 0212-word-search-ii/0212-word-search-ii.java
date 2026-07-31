class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();
    List<String> ans = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.child[idx] == null)
                    node.child[idx] = new TrieNode();
                node = node.child[idx];
            }
            node.word = word;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root);
            }
        }

        return ans;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return;

        char ch = board[i][j];

        if (ch == '#')
            return;

        node = node.child[ch - 'a'];

        if (node == null)
            return;

        if (node.word != null) {
            ans.add(node.word);
            node.word = null;
        }

        board[i][j] = '#';

        dfs(board, i + 1, j, node);
        dfs(board, i - 1, j, node);
        dfs(board, i, j + 1, node);
        dfs(board, i, j - 1, node);

        board[i][j] = ch;
    }
}
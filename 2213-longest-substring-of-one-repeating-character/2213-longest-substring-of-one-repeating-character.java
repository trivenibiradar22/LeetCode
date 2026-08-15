class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int max;
        int length;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            max = 1;
            length = 1;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {

        s = str.toCharArray();

        int n = s.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            s[index] = ch;

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(s[left]);
            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index, char ch) {

        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {

        Node result = new Node(a.leftChar);

        result.length = a.length + b.length;

        result.leftChar = a.leftChar;
        result.rightChar = b.rightChar;

        result.prefix = a.prefix;
        result.suffix = b.suffix;

        result.max = Math.max(a.max, b.max);

        if (a.rightChar == b.leftChar) {

            result.max = Math.max(
                result.max,
                a.suffix + b.prefix
            );

            if (a.prefix == a.length) {
                result.prefix = a.length + b.prefix;
            }

            if (b.suffix == b.length) {
                result.suffix = a.suffix + b.length;
            }
        }

        return result;
    }
}
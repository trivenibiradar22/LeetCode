import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> origAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            origAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origAdj.get(parent[i]).add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] lastChar = new int[26];
        Arrays.fill(lastChar, -1);

        dfsAssignParents(0, origAdj, s, parent, newParent, lastChar);

        List<List<Integer>> finalAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            finalAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalAdj.get(newParent[i]).add(i);
        }

        int[] answer = new int[n];
        dfsSubtreeSize(0, finalAdj, answer);
        return answer;
    }

    private void dfsAssignParents(
            int u,
            List<List<Integer>> origAdj,
            String s,
            int[] origParent,
            int[] newParent,
            int[] lastChar) {
        int ch = s.charAt(u) - 'a';
        int prevAncestor = lastChar[ch];

        if (u > 0) {
            newParent[u] = (prevAncestor != -1) ? prevAncestor : origParent[u];
        }

        lastChar[ch] = u;
        for (int v : origAdj.get(u)) {
            dfsAssignParents(v, origAdj, s, origParent, newParent, lastChar);
        }
        lastChar[ch] = prevAncestor;
    }

    private int dfsSubtreeSize(int u, List<List<Integer>> finalAdj, int[] answer) {
        int size = 1;
        for (int v : finalAdj.get(u)) {
            size += dfsSubtreeSize(v, finalAdj, answer);
        }
        answer[u] = size;
        return size;
    }
}
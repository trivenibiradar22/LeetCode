import java.util.*;

class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();

        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[t.length];
        int stars = 0;

        while (stars < t.length) {
            boolean replaced = false;

            for (int i = 0; i <= t.length - s.length; i++) {
                if (!visited[i] && canReplace(t, i, s)) {
                    stars += doReplace(t, i, s.length);
                    visited[i] = true;
                    replaced = true;
                    res.add(i);

                    if (stars == t.length) {
                        break;
                    }
                }
            }

            if (!replaced) {
                return new int[0];
            }
        }

        Collections.reverse(res);

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    private boolean canReplace(char[] target, int pos, char[] stamp) {
        for (int i = 0; i < stamp.length; i++) {
            if (target[pos + i] != '*' && target[pos + i] != stamp[i]) {
                return false;
            }
        }
        return true;
    }

    private int doReplace(char[] target, int pos, int len) {
        int count = 0;

        for (int i = 0; i < len; i++) {
            if (target[pos + i] != '*') {
                target[pos + i] = '*';
                count++;
            }
        }

        return count;
    }
}
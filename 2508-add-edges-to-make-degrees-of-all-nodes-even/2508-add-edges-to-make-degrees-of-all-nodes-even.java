import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] degree = new int[n + 1];
        Set<Long> set = new HashSet<>();

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            degree[u]++;
            degree[v]++;

            long key = ((long) Math.min(u, v) << 20) | Math.max(u, v);
            set.add(key);
        }

        List<Integer> odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 != 0) {
                odd.add(i);
            }
        }

        if (odd.size() == 0) return true;

        if (odd.size() == 2) {
            int a = odd.get(0);
            int b = odd.get(1);

            if (!set.contains(key(a, b))) {
                return true;
            }

            for (int i = 1; i <= n; i++) {
                if (i != a && i != b &&
                    !set.contains(key(a, i)) &&
                    !set.contains(key(b, i))) {
                    return true;
                }
            }

            return false;
        }

        if (odd.size() == 4) {
            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            return (!set.contains(key(a, b)) && !set.contains(key(c, d))) ||
                   (!set.contains(key(a, c)) && !set.contains(key(b, d))) ||
                   (!set.contains(key(a, d)) && !set.contains(key(b, c)));
        }

        return false;
    }

    private long key(int a, int b) {
        return ((long) Math.min(a, b) << 20) | Math.max(a, b);
    }
}
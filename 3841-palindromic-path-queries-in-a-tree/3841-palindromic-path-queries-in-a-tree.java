import java.util.*;

class Solution {
    private List<List<Integer>> adj;
    private int[] tin, tout, head, parent, depth, pos, heavy, size;
    private int timer;
    private int[] tree, lazy;

    private void dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        size[u] = 1;
        int maxSub = 0;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs1(v, u, d + 1);
                size[u] += size[v];
                if (size[v] > maxSub) {
                    maxSub = size[v];
                    heavy[u] = v;
                }
            }
        }
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = ++timer;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            // Find which node is at position l in tour
            // We need a mapping from position to node
            return;
        }
    }

    // A complete efficient HLD + Segment Tree solution with 26-bit XOR mask for paths
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        timer = 0;

        dfs1(0, -1, 0);
        dfs2(0, 0);

        int[] segTree = new int[4 * n];
        int[] nodeAtPos = new int[n + 1];
        char[] sArr = s.toCharArray();
        int[] charMask = new int[n];
        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (sArr[i] - 'a');
        }

        class SegTree {
            int[] st = new int[4 * n];
            void build(int node, int l, int r) {
                if (l == r) {
                    st[node] = charMask[nodeAtPos[l]];
                    return;
                }
                int mid = (l + r) / 2;
                build(2 * node, l, mid);
                build(2 * node + 1, mid + 1, r);
                st[node] = st[2 * node] ^ st[2 * node + 1];
            }
            void update(int node, int l, int r, int idx, int val) {
                if (l == r) {
                    st[node] = val;
                    return;
                }
                int mid = (l + r) / 2;
                if (idx <= mid) update(2 * node, l, mid, idx, val);
                else update(2 * node + 1, mid + 1, r, idx, val);
                st[node] = st[2 * node] ^ st[2 * node + 1];
            }
            int query(int node, int l, int r, int ql, int qr) {
                if (ql > r || qr < l) return 0;
                if (ql <= l && r <= qr) return st[node];
                int mid = (l + r) / 2;
                return query(2 * node, l, mid, ql, qr) ^ query(2 * node + 1, mid + 1, r, ql, qr);
            }
        }

        for (int i = 0; i < n; i++) {
            nodeAtPos[pos[i]] = i;
        }

        SegTree st = new SegTree();
        st.build(1, 1, n);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                charMask[u] = 1 << (c - 'a');
                st.update(1, 1, n, pos[u], charMask[u]);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathXor = 0;
                int curU = u, curV = v;
                while (head[curU] != head[curV]) {
                    if (depth[head[curU]] > depth[head[curV]]) {
                        int temp = curU; curU = curV; curV = temp;
                    }
                    pathXor ^= st.query(1, 1, n, pos[head[curV]], pos[curV]);
                    curV = parent[head[curV]];
                }
                if (depth[curU] > depth[curV]) {
                    int temp = curU; curU = curV; curV = temp;
                }
                pathXor ^= st.query(1, 1, n, pos[curU], pos[curV]);

                if ((pathXor & (pathXor - 1)) == 0) {
                    ans.add(true);
                } else {
                    ans.add(false);
                }
            }
        }
        return ans;
    }
}
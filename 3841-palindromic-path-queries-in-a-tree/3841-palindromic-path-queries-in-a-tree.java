
import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s,
                                        String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] parent = new int[n];
        int[] depth = new int[n];

        int log = 1;
        while ((1 << log) <= n) log++;

        int[][] up = new int[log][n];

        // Iterative DFS
        int[] stack = new int[n];
        int[] iter = new int[n];
        Arrays.fill(parent, -1);

        int top = 0, timer = 0;
        stack[top++] = 0;
        parent[0] = 0;
        tin[0] = timer++;

        while (top > 0) {
            int u = stack[top - 1];

            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                tin[v] = timer++;
                stack[top++] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        up[0][0] = 0;

        for (int k = 1; k < log; k++) {
            for (int u = 0; u < n; u++) {
                up[k][u] = up[k - 1][up[k - 1][u]];
            }
        }

        Fenwick fw = new Fenwick(n);
        char[] chars = s.toCharArray();
        int[] mask = new int[n];

        for (int u = 0; u < n; u++) {
            mask[u] = 1 << (chars[u] - 'a');
            fw.rangeXor(tin[u], tout[u], mask[u]);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);
                int newMask = 1 << (c - 'a');
                int delta = mask[u] ^ newMask;

                fw.rangeXor(tin[u], tout[u], delta);
                mask[u] = newMask;
                chars[u] = c;
            } else {
                int v = Integer.parseInt(p[2]);
                int ancestor = lca(u, v, depth, up);

                int pathMask = fw.pointQuery(tin[u])
                             ^ fw.pointQuery(tin[v])
                             ^ mask[ancestor];

                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }

    private int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void add(int i, int val) {
            for (i++; i <= n + 1; i += i & -i) {
                bit[i] ^= val;
            }
        }

        int prefixXor(int i) {
            int ans = 0;
            for (; i > 0; i -= i & -i) {
                ans ^= bit[i];
            }
            return ans;
        }

        void rangeXor(int l, int r, int val) {
            add(l, val);
            add(r, val);
        }

        int pointQuery(int i) {
            return prefixXor(i + 1);
        }
    }
}

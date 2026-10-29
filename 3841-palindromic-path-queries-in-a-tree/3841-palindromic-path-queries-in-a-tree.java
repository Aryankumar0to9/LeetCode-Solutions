import java.util.*;

class Solution {
    class SegmentTree {
        int n;
        int[] tree;

        public SegmentTree(int size) {
            n = size;
            tree = new int[4 * n];
        }

        public void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        public int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, l, r) ^ query(2 * node + 1, mid + 1, end, l, r);
        }
    }

    private List<List<Integer>> adj;
    private int[] parent, depth, heavy, head, pos;
    private int curPos;

    private int dfs(int u, int p, int d, char[] chars) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtree = 0;
        for (int v : adj.get(u)) {
            if (v != p) {
                int subtree = dfs(v, u, d + 1, chars);
                size += subtree;
                if (subtree > maxSubtree) {
                    maxSubtree = subtree;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void decompose(int u, int h, char[] chars, int[] initialVals) {
        head[u] = h;
        pos[u] = curPos;
        initialVals[curPos++] = 1 << (chars[u] - 'a');

        if (heavy[u] != -1) {
            decompose(heavy[u], h, chars, initialVals);
        }

        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v, chars, initialVals);
            }
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        curPos = 0;

        char[] chars = s.toCharArray();
        dfs(0, -1, 0, chars);

        int[] initialVals = new int[n];
        decompose(0, 0, chars, initialVals);

        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            st.update(1, 0, n - 1, i, initialVals[i]);
        }

        List<Boolean> result = new ArrayList<>();

        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("query")) {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int xorSum = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] > depth[head[v]]) {
                        int temp = u; u = v; v = temp;
                    }
                    xorSum ^= st.query(1, 0, n - 1, pos[head[v]], pos[v]);
                    v = parent[head[v]];
                }
                if (depth[u] > depth[v]) {
                    int temp = u; u = v; v = temp;
                }
                xorSum ^= st.query(1, 0, n - 1, pos[u], pos[v]);

                // Check if at most one bit is set
                result.add((xorSum & (xorSum - 1)) == 0);
            } else if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                chars[u] = newChar;
                st.update(1, 0, n - 1, pos[u], 1 << (newChar - 'a'));
            }
        }

        return result;
    }
}
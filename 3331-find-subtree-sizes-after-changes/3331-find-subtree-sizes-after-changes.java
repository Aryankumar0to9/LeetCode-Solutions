import java.util.*;

class Solution {
    List<Integer>[] originalTree;
    List<Integer>[] newTree;
    int[] newParent;
    int[] size;
    String s;
    int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        originalTree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            originalTree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            originalTree[parent[i]].add(i);
        }

        newParent = parent.clone();
        last = new int[26];
        Arrays.fill(last, -1);

        // Find new parents
        dfsChange(0);

        // Build modified tree
        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        size = new int[n];
        dfsSize(0);

        return size;
    }

    void dfsChange(int node) {
        int c = s.charAt(node) - 'a';

        int previous = last[c];

        // If a same-character ancestor exists
        if (previous != -1) {
            newParent[node] = previous;
        }

        last[c] = node;

        for (int child : originalTree[node]) {
            dfsChange(child);
        }

        // Restore previous ancestor after DFS
        last[c] = previous;
    }

    int dfsSize(int node) {
        size[node] = 1;

        for (int child : newTree[node]) {
            size[node] += dfsSize(child);
        }

        return size[node];
    }
}
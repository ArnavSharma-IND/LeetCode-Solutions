
import java.util.*;

class Solution {

    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] last;
    int[] answer;
    int[] newParent;
    int[] originalParent;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        this.s = s;
        this.originalParent = parent; // FIX

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        newParent = new int[n];
        newParent[0] = -1;

        dfs(0);

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        answer = new int[n];

        calculateSize(0);

        return answer;
    }

    void dfs(int node) {

        int c = s.charAt(node) - 'a';

        int previous = last[c];

        if (last[c] != -1) {
            newParent[node] = last[c];
        } else if (node != 0) {
            newParent[node] = originalParent[node];
        }

        last[c] = node;

        for (int child : tree[node]) {
            dfs(child);
        }

        last[c] = previous;
    }

    int calculateSize(int node) {

        int size = 1;

        for (int child : newTree[node]) {
            size += calculateSize(child);
        }

        answer[node] = size;

        return size;
    }
}

package problems.lc1522;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
// Definition for a Node.
*/
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    int result;

    int dfs(Node root) {
        if(root == null) {
            return 0;
        }
        List<Integer> childPath = new ArrayList<>();
        for (Node child : root.children) {
            childPath.add(dfs(child));
        }
        childPath.sort(Comparator.reverseOrder());
        int m1 = !childPath.isEmpty() ? childPath.getFirst() : 0;
        int m2 = childPath.size() > 1 ? childPath.get(1) : 0;
        result = Math.max(result, m1 + m2 + 1);
        return m1 + 1;
    }

    int dfs2(Node root) {
        if(root == null) {
            return 0;
        }
        int m1 = 0, m2 = 0;
        for (Node child : root.children) {
            int curr = dfs(child);
            if(curr > m1) {
                m2 = m1;
                m1 = curr;
            } else if(curr > m2) {
                m2 = curr;
            }
        }
        result = Math.max(result, m1 + m2 + 1);
        return m1 + 1;
    }

    public int diameter(Node root) {
        result = 0;
        dfs2(root);
        return result - 1;
    }
}

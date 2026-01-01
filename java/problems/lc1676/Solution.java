package problems.lc1676;


import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {

    TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        findLCA(root, new HashSet<>(List.of(nodes)));
        return lca;
    }

    public int findLCA(TreeNode root, Set<TreeNode> nodes) {
        if(root == null) return 0;
        int left = findLCA(root.left, nodes);
        int right = findLCA(root.right, nodes);
        int count = 0;
        if(nodes.contains(root)) {
            count = 1;
        }
        if(left + right + count == nodes.size()) {
            lca = lca == null ? root : lca;
        }
        return left + right + count;
    }
}

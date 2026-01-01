package problems.lc1644;

import com.sun.source.tree.Tree;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {

    TreeNode lca;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return lca;
    }

    public TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        if(left != null && right != null) {
            lca = root;
            return root;
        }

        if((left != null || right != null) && (root.val == p.val || root.val == q.val)) {
            lca = root;
            return root;
        }

        if(root.val == p.val || root.val == q.val) return root;
        return left != null ? left : right;
    }
}

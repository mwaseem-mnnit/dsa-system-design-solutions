package problems.lc236;

import com.sun.source.tree.Tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    TreeNode lca;

    public Solution() {
        this.lca = null;
    }

    public TreeNode findLCA(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) {
            return null;
        }
        boolean mid = false;
        if(p.val == node.val || q.val == node.val) {
            mid = true;
        }
        TreeNode left = findLCA(node.left, p, q);
        TreeNode right = findLCA(node.right, p, q);
        if(mid && (left != null || right != null)) {
            this.lca = node;
            return node;
        } else if(left != null && right != null){
            this.lca = node;
            return node;
        } else {
            return left != null ? left : mid == true ? node : right;
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findLCA(root, p, q);
        return lca;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
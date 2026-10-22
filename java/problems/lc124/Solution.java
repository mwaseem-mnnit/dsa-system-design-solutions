package problems.lc124;

/**
 * Definition for a binary tree node.
 */
class Solution {

    int max;
    public int maxPathSum(TreeNode root) {
        this.max = Integer.MIN_VALUE;
        iterateDFS(root);
        return this.max;
    }

    private Integer iterateDFS(TreeNode root) {
        if(root == null) {
            return null;
        }
        Integer left = iterateDFS(root.left);
        Integer right = iterateDFS(root.right);
        int val = root.val;
        int valWithLeft = root.val + ((left == null) ? 0 : left);
        int valWithRight = root.val + ((right == null) ? 0 : right);
        int total = (valWithLeft + valWithRight) - val;
        this.max = Math.max(this.max, Math.max(val, Math.max(total, Math.max(valWithLeft, valWithRight))));
        return Math.max(val, Math.max(valWithLeft, valWithRight));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
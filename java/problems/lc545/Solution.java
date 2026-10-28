package problems.lc545;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Definition for a binary tree node.
 */
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

class Solution {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        TreeMap<Integer, Integer> leftBoundary = new TreeMap<>();
        TreeMap<Integer, Integer> rightBoundary = new TreeMap<>((a, b) -> b - a);
        List<Integer> result = new ArrayList<>();
        result.add(root.val);

        this.computeLeftNodeOrder(root.left, 0, leftBoundary);
        for (Map.Entry<Integer, Integer> entry : leftBoundary.entrySet()) {
            if (entry.getValue() > -1002) {
                result.add(entry.getValue());
            }
        }

        this.computeLeafOrder(root.left, result);
        this.computeLeafOrder(root.right, result);

        this.computeRightNodeOrder(root.right, 0, rightBoundary);
        for (Map.Entry<Integer, Integer> entry : rightBoundary.entrySet()) {
            if (entry.getValue() > -1002) {
                result.add(entry.getValue());
            }
        }

        return result;
    }

    private void computeLeafOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(node.val);
            return;
        }
        this.computeLeafOrder(node.left, result);
        this.computeLeafOrder(node.right, result);
    }

    private void computeLeftNodeOrder(TreeNode node, int level, TreeMap<Integer, Integer> leftBoundary) {
        if(node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (!leftBoundary.containsKey(level)) {
                leftBoundary.put(level, -1002);
            }
            return;
        }
        if (!leftBoundary.containsKey(level)) {
            leftBoundary.put(level, node.val);
        }
        this.computeLeftNodeOrder(node.left, level + 1, leftBoundary);
        this.computeLeftNodeOrder(node.right, level + 1, leftBoundary);
    }

    private void computeRightNodeOrder(TreeNode node, int level, TreeMap<Integer, Integer> rightBoundary) {
        if(node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (!rightBoundary.containsKey(level)) {
                rightBoundary.put(level, -1002);
            }
            return;
        }
        if (!rightBoundary.containsKey(level)) {
            rightBoundary.put(level, node.val);
        }
        this.computeRightNodeOrder(node.right, level + 1, rightBoundary);
        this.computeRightNodeOrder(node.left, level + 1, rightBoundary);
    }


}
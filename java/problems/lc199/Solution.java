package problems.lc199;

import java.util.*;

/**
 * Definition for a binary tree node.
 */
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
class Pair<U, V> {
    U first;
    V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        int currentLevel = 1;
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> top = queue.poll();
            if(top.second == currentLevel) {
                result.add(top.first.val);
                currentLevel += 1;
            }
            if(top.first.right != null) {
                queue.add(new Pair<>(top.first.right, top.second + 1));
            }
            if(top.first.left != null) {
                queue.add(new Pair<>(top.first.left, top.second + 1));
            }
        }
        return result;
    }
}
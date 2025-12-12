import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    public List<Integer> dfs(TreeNode root, int target) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null && root.val != target) {
            stack.push(root);
            if (root.val > target) {
                root = root.left;
            } else if (root.val < target) {
                root = root.right;
            }
        }

        if (root != null) {
            return new ArrayList<>(Arrays.asList(target, target));
        }

        int min = -1, max = -1;
        TreeNode node = stack.pop();
        if(node.val < target) {
            max = node.val;
            while (!stack.isEmpty()) {
                TreeNode parent = stack.pop();
                if (parent.val > target) {
                    min = parent.val;
                    break;
                }
            }
        } else {
            min = node.val;
            while (!stack.isEmpty()) {
                TreeNode parent = stack.pop();
                if (parent.val < target) {
                    min = parent.val;
                    break;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(max, min));
    }

    public void inorder(TreeNode root, List<Integer> sortedList) {
        if (root == null) {
            return;
        }
        inorder(root.left, sortedList);
        sortedList.add(root.val);
        inorder(root.right, sortedList);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();
        this.inorder(root, sortedList);
        for (Integer target : queries) {
            int value = Collections.binarySearch(sortedList, target);
            if (value < 0) {
                int pos = -value - 1, min = -1, max = -1;
                if (pos >= sortedList.size()) {
                    max = sortedList.getLast();
                } else if (pos == 0) {
                    min = sortedList.getFirst();
                } else {
                    if (sortedList.get(pos) < target) {
                        max = sortedList.get(pos);
                        min = sortedList.get(pos + 1);
                    } else {
                        max = sortedList.get(pos - 1);
                        min = sortedList.get(pos);
                    }
                }
                result.add(Arrays.asList(max, min));
            } else {
                result.add(Arrays.asList(sortedList.get(value), sortedList.get(value)));
            }
        }
        return result;
    }

    public static void main(String[] args) {

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
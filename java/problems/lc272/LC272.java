package problems.lc272;
import java.util.*;

class Solution {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<TreeNode> queue = new PriorityQueue<>(Comparator.comparingDouble(a -> Math.abs(a.val - target)));
        List<Integer> result = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        TreeNode node = root;
        queue.add(node);
        visited.add(node);
        while (node != null && node.val != target) {
            if (node.val < target) {
                node = node.right;
            } else {
                node = node.left;
            }
            if (node != null) {
                queue.add(node);
                visited.add(node);
            }
        }
        while(k > 0) {
            TreeNode poll = queue.poll();
            result.add(poll.val);
            this.fillNearestNeighbour(poll, queue, visited);
            k--;
        }
        return result;
    }

    private void fillNearestNeighbour(TreeNode poll, PriorityQueue<TreeNode> queue, Set<TreeNode> visited) {
        TreeNode left = poll.left;
        TreeNode right = poll.right;
        while(left != null && !visited.contains(left)) {
            queue.add(left);
            visited.add(left);
            left = left.right;
        }

        while(right != null  && !visited.contains(right)) {
            queue.add(right);
            visited.add(right);
            right = right.left;
        }
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
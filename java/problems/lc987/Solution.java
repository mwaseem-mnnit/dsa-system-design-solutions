package problems.lc987;

import java.lang.reflect.Array;
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

class QueueNode {
    TreeNode node;
    int col;
    int row;
    QueueNode(TreeNode node, int col, int row) {
        this.node = node;
        this.col = col;
        this.row = row;
    }
}

public class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, TreeMap<Integer, ArrayList<Integer>>> colAdjList = new TreeMap<>();

        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(root, 0, 0));
        while (!queue.isEmpty()) {
            QueueNode top = queue.poll();
            TreeNode node = top.node;
            TreeMap<Integer, ArrayList<Integer>> columnMap =  colAdjList.getOrDefault(top.col, new TreeMap<>());
            ArrayList<Integer> rowSet = columnMap.getOrDefault(top.row, new ArrayList<>());
            rowSet.add(node.val);
            columnMap.put(top.row, rowSet);
            colAdjList.put(top.col, columnMap);
            TreeNode left = node.left;
            TreeNode right = node.right;
            if(left != null) {
                queue.add(new QueueNode(left, top.col - 1, top.row + 1));
            }
            if(right != null) {
                queue.add(new QueueNode(right, top.col + 1, top.row + 1));
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, ArrayList<Integer>> colMap : colAdjList.values()) {
            List<Integer> colResult = new ArrayList<>();
            for (ArrayList<Integer> rowMap : colMap.values()) {
                Collections.sort(rowMap);
                colResult.addAll(rowMap);
            }
            result.add(colResult);
        }
        return result;
    }
}

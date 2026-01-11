package problems.lc426;


// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

class Solution {

    private Node head;

    public Node performDFS(Node treeNode, Node listNode) {
        if(treeNode == null) {
            return listNode;
        }

        listNode = performDFS(treeNode.left, listNode);

        Node newNode = new Node(treeNode.val, null, null);

        if(listNode != null) {
            listNode.right = newNode;
            newNode.left = listNode;
        } else {
            head = newNode;
        }
        listNode = newNode;

        listNode = performDFS(treeNode.right, listNode);

        return listNode;
    }

    public Node treeToDoublyList(Node root) {
        Node tail = performDFS(root, null);
        if(head != null) head.left = tail;
        if(tail != null) tail.right = head;
        return head;
    }
}
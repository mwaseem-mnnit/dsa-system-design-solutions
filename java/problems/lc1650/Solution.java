package problems.lc1650;

import java.util.HashSet;
import java.util.Set;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}

class Solution {
    public Node lowestCommonAncestor1(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while(p != null) {
            set.add(p);
            p = p.parent;
        }

        while(q != null) {
            if(set.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null? q : a.parent;
            b = b == null? p : b.parent;
        }
        return a;
    }
}

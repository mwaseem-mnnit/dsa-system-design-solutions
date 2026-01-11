package problems.lc25;

import java.util.List;

/**
 * Definition for singly-linked list.
 */
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Pair {
    ListNode start;
    ListNode end;
    ListNode nextNode;

    public Pair(ListNode start, ListNode end, ListNode nextNode) {
        this.start = start;
        this.end = end;
        this.nextNode = nextNode;
    }
}

class Solution {

    boolean findKthNode(ListNode node, int k) {
        int i=1;
        while (node != null && i < k) {
            node = node.next;
            i++;
        }
        return i >= k && node != null;
    }

    Pair reverseK(ListNode node, int k) {
        ListNode prev = null, curr = node, next, end = node;
        int i = 0;
        while (i < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }
        return new Pair(prev, end, curr);
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1) {
            return head;
        }
        ListNode node = head;
        Pair previousPair = null;
        boolean isNewHead = false;
        while (findKthNode(node, k)) {
            Pair pair = reverseK(node, k);
            if(!isNewHead) {
                head = pair.start;
                isNewHead = true;
            }
            if(previousPair != null) {
                previousPair.end.next = pair.start;
            }
            previousPair = pair;
            node = pair.nextNode;
        }
        if(previousPair != null) {
            previousPair.end.next = node;
        }
        return head;
    }
}
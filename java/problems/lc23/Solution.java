package problems.lc23;

/**
 * Definition for singly-linked list.
 *
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode start = null, current = null, nextNode;
        while (true) {
            nextNode = findNextNode(lists);
            if(nextNode == null) {
                break;
            }
            if(start == null) {
                start = nextNode;
                current = nextNode;
            } else {
                current.next = nextNode;
                current = nextNode;
            }
        }
        return start;
    }

    ListNode findNextNode(ListNode[] lists) {
        int min = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null && lists[i].val < min) {
                min = lists[i].val;
                index = i;
            }
        }
        if(index == -1) {
            return null;
        }
        ListNode nextNode = lists[index];
        lists[index] = nextNode.next;
        nextNode.next = null;
        return nextNode;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
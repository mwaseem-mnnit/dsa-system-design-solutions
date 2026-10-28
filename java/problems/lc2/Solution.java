package problems.lc2;

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

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = null, result = null;
        ListNode l1Head = l1, l2Head = l2;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val: 0) + carry;
            carry = 0;
            if(sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            }
            ListNode newNode = new ListNode(sum);
            if(result != null) {
                result.next = newNode;
            } else {
                resultHead = newNode;
            }
            result = newNode;
            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);
        }
        if(carry > 0) {
            result.next = new ListNode(carry);
        }
        return resultHead;
    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {

            // Step 1: Find the kth node
            ListNode kth = groupPrev;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            // Not enough nodes remaining
            if (kth == null) {
                break;
            }

            // Step 2: Save the next group's starting node
            ListNode groupNext = kth.next;

            // Step 3: Reverse the current group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {

                ListNode temp = curr.next;

                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Step 4: Connect the reversed group
            ListNode temp = groupPrev.next;

            groupPrev.next = kth;

            // Move to the next group
            groupPrev = temp;
        }

        return dummy.next;
    }
}

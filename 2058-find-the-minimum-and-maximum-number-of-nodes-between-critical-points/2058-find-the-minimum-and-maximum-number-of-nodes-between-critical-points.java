/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        
        int firstIndex = -1;
        int prevIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        int currentIndex = 2;

        while (curr.next != null) {
            // Check for local maxima or local minima
            if ((curr.val > prev.val && curr.val > curr.next.val) || 
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                if (firstIndex == -1) {
                    firstIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevIndex);
                }
                prevIndex = currentIndex;
            }
            
            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // If fewer than two critical points were found
        if (firstIndex == -1 || prevIndex == firstIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}
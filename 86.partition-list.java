/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
 *
 * https://leetcode.com/problems/partition-list/description/
 *
 * algorithms
 * Medium (35.61%)
 * Total Accepted:    145.9K
 * Total Submissions: 409.6K
 * Testcase Example:  '[1,4,3,2,5,2]\n3'
 *
 * Given a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * Example:
 * 
 * 
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 * 
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode pre1 = dummy1;
        ListNode pre2 = dummy2;

        while (head != null) {
            if (head.val < x) {
                pre1.next = head;
                pre1 = pre1.next;        
            }
            else {
                pre2.next = head;
                pre2 = pre2.next;
            }
            head = head.next;
        }
        pre2.next = null;
        pre1.next = dummy2.next;
        return dummy1.next;
    }
}

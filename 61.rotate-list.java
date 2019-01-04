class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int len = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        k = k % len;
        ListNode slow = dummy;
        for (int i = 0; i < len - k; i++) {
            slow = slow.next;
        }
        cur.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
        
    }

    


}

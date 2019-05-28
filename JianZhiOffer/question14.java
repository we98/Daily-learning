/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    //快慢指针
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null || k <= 0){
            return null;
        }
        ListNode fast = head;
        while(--k > 0){
            fast = fast.next;
            if(fast == null){
                return null;
            }
        }
        ListNode slow = head;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
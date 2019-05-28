/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //四步，循环结束条件为front为空
        ListNode front = head;
        ListNode back = null;
        while(front != null){
            ListNode temp = front.next;
            front.next = back;
            back = front;
            front = temp;
        }
        return back;
    }
}
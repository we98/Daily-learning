public class Solution{

    public ListNode sortList(ListNode head) {
        //return sortListByPartition(head);
        return sortListByMerge(head);
    }

    public ListNode sortListByMerge(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        ListNode leftHead = sortListByMerge(head);
        ListNode rightHead = sortListByMerge(head2);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (leftHead != null && rightHead != null){
            if(leftHead.val < rightHead.val){
                tail.next = leftHead;
                tail = leftHead;
                leftHead = leftHead.next;
            }
            else{
                tail.next = rightHead;
                tail = rightHead;
                rightHead = rightHead.next;
            }
        }
        if(leftHead == null){
            tail.next = rightHead;
        }
        else{
            tail.next = leftHead;
        }
        return dummy.next;
    }

    /**
     * 按照划分的思想做，一定要注意细节，不要直接就按照第一个的值分成两段链表，一定要先将第一个拿出来，再将剩下的划分，这样可以保证一次至少完成一个
     * 而不拿出来的话有可能每次都完成0个，导致栈溢出
     * @param head
     * @return
     */
    private ListNode sortListByPartition(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode smallerOrEqualStart = new ListNode(0), smallerOrEqualEnd = smallerOrEqualStart;
        ListNode largerStart = new ListNode(0), largerEnd = largerStart;
        int key = head.val;
        //这是快排的细节，每次至少要完成一个才行
        ListNode current = head.next;
        while (current != null){
            if(current.val <= key){
                smallerOrEqualEnd.next = current;
                smallerOrEqualEnd = current;
            }
            else{
                largerEnd.next = current;
                largerEnd = current;
            }
            current = current.next;
        }
        //这是划分时的细节，一定要将两个end都置为null，否则会出错
        smallerOrEqualEnd.next = null;
        largerEnd.next = null;
        ListNode leftStart = sortListByPartition(smallerOrEqualStart.next);
        ListNode rightStart = sortListByPartition(largerStart.next);
        if(leftStart != null){
            current = leftStart;
            while (current.next != null){
                current = current.next;
            }
            current.next = head;
            head.next = rightStart;
            return leftStart;
        }
        else{
            head.next = rightStart;
            return head;
        }
    }

}

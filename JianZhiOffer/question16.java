/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null && list2 == null){
            return null;
        }
        else if(list1 == null){
            return list2;
        }
        else if(list2 == null){
            return list1;
        }
        //这里可以声明一个不空头节点ListNode(-1)，然后使用一个引用拷贝记录下这个头节点，如root
        //这样就不会最开始的这个if了，简洁，最后返回root.next即可
        ListNode head = null;
        ListNode tail = null;
        if(list1.val <= list2.val){
            head = tail = list1;
            list1 = list1.next;
        }
        else{
            head = tail = list2;
            list2 = list2.next;
        }
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                //这里不要只顾着改变tail.next，而忘了改变tail本身
                tail = tail.next = list1;
                list1 = list1.next;
            }
            else{
                tail = tail.next = list2;
                list2 = list2.next;
            }
        }
        tail.next = (list1 != null ? list1 : list2);
        return head;
    }
}
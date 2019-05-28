/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
import java.util.Collections;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//注释掉的方法使用了数据结构，未注释的方法将数组链表反转后打印
//        if(listNode == null){
//            return new ArrayList<>(0);
//        }
//        ArrayList<Integer> result = new ArrayList<>();
//        while(listNode != null){
//            result.add(listNode.val);
//            listNode = listNode.next;
//        }
//        Collections.reverse(result);
//        return result;
        if(listNode == null){
            return new ArrayList<Integer>(0);
        }
        int length = 0;
        ListNode back = null;
        while(listNode != null){
            ListNode temp = listNode.next;
            listNode.next = back;
            back = listNode;
            listNode = temp;
            ++length;
        }
        listNode = back;
        ArrayList<Integer> result = new ArrayList<>(length);
        while(back != null){
            result.add(back.val);
            back = back.next;
        }
        back = listNode;
        listNode = null;
        while(back != null){
            ListNode temp = back.next;
            back.next = listNode;
            listNode = back;
            back = temp;
        }
        return result;
    }
}
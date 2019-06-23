package question3;

import java.util.ArrayList;

/**
 * ����һ������������ֵ��β��ͷ��˳�򷵻�һ��ArrayList��
 * 
 * ������ʹ�õݹ�Ҳ��ʹ��ջ�ģ��������õķ����ǽ��������򣬽�������ArrayList�С�
 * 
 * @author CGWEI
 *
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
	public static void main(String[] args) {
		ListNode start = generateLinkedList(18);
        printListNodes(start);
        System.out.println(printListFromTailToHead(start));
	}
	
	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode == null){
            throw new RuntimeException("null pointer");
        }
        if(listNode.next == null){
            ArrayList<Integer> a = new ArrayList<>(1);
            a.add(listNode.val);
            return a;
        }
        ListNode back = null;
        ListNode front = listNode;
        int length = 0;
        while (front != null){
            ListNode temp = front.next;
            front.next = back;
            back = front;
            front = temp;
            length++;
        }
        ArrayList<Integer> arrayList = new ArrayList<>(length);
        while (back != null){
            arrayList.add(back.val);
            back=back.next;
        }
        return arrayList;
    }
	private static void printListNodes(ListNode n){
        while (n != null){
            System.out.print(n.val);
            System.out.print("  ");
            n = n.next;
        }
        System.out.println();
    }
	private static ListNode generateLinkedList(int len){
        ListNode first = new ListNode(1);
        ListNode start = first;
        start.val = 1;
        for(int i = 1; i < len; i++){
            start.next = new ListNode(i + 1);
            start = start.next;
        }
        return first;
    }
}
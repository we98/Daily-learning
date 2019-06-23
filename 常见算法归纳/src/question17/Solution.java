package question17;

import java.util.ArrayList;


/**

 * @author CGWEI
 *
 */
public class Solution {
	
	public static class Node {
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * 链表的荷兰国旗问题
	 * 将链表复制到数组中，在数组中进行划分，然后连接即可
	 * @param head
	 * @param flag
	 */
	public static Node linkedListPartition(Node head, int flag) {
		if(head == null) {
			return null;
		}
		else if(head.next == null) {
			return head;
		}
		ArrayList<Node> arrayList = new ArrayList<>();
		while(head != null) {
			arrayList.add(head);
			head = head.next;
		}
		partitionInArrayList(arrayList, flag);
		for(int i = 0; i < arrayList.size() - 1; i++) {
			arrayList.get(i).next = arrayList.get(i+1);
		}
		head = arrayList.get(0);
		arrayList.get(arrayList.size()-1).next = null;
		return head;
	}
	private static void partitionInArrayList(ArrayList<Node> arrayList, int flag) {
		int less = -1;
		int more = arrayList.size();
		int current = 0;
		while(current < more) {
			int value = arrayList.get(current).value;
			if(value == flag) {
				current++;
			}
			else if(value < flag) {
				swapInArrayList(arrayList, ++less, current++);
			}
			else {
				swapInArrayList(arrayList, --more, current);
			}
		}
	}
	private static void swapInArrayList(ArrayList<Node> arrayList, int i, int j) {
		Node temp = arrayList.get(i);
		arrayList.set(i, arrayList.get(j));
		arrayList.set(j, temp);
	}
	
	/**
	 * 链表的荷兰国旗问题
	 * 要求时间复杂度O(N)，空间复杂度O(1)，且划分后仍然稳定
	 * 我们知道在数组中无法做到稳定，然而在链表中是可以实现的
	 * 这个问题的实际解决方案就是通过三个链表记录小于等于和大于，但是因为链表的特殊性，只需要生成两个节点就可以了，所以可以通过O(1)的空间复杂度实现稳定性
	 * 而在数组中是不可行的
	 * @param head
	 * @param flag
	 */
	public static Node linkedListPartitionWithDifficulty(Node head, int flag) {
		if(head == null) {
			return null;
		}
		else if(head.next == null) {
			return head;
		}
		Node smallerStart = null;
		Node smallerEnd = null;
		Node equalStart = null;
		Node equalEnd = null;
		Node largerStart = null;
		Node largerEnd = null;
		while(head != null) {
			int value = head.value;
			if(value < flag) {
				if(smallerEnd == null) {
					smallerStart = head;
				}
				else {
					smallerEnd.next = head;
				}
				smallerEnd = head;
			}
			else if(value == flag) {
				if(equalEnd == null) {
					equalStart = head;
				}
				else {
					equalEnd.next = head;
				}
				equalEnd = head;
			}
			else {
				if(largerEnd == null) {
					largerStart = head;
				}
				else {
					largerEnd.next = head;
				}
				largerEnd = head;
			}
			head = head.next;
		}
		
		//这句不能忘掉  否则可能会导致死循环
		if(largerEnd != null) {
			largerEnd.next = null;
		}
		
		if(smallerEnd != null && equalEnd != null) {
			smallerEnd.next = equalStart;
			equalEnd.next = largerStart;
			return smallerStart;
		}
		else if(smallerEnd == null && equalEnd != null) {
			equalEnd.next = largerStart;
			return equalStart;
		}
		else if(smallerEnd != null && equalEnd == null) {
			smallerEnd.next = largerStart;
			return smallerStart;
		}
		else {
			return largerStart;
		}
		
	}
	
	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		//head1 = linkedListPartition(head1, 5);
		head1 = linkedListPartitionWithDifficulty(head1, 5);
		printLinkedList(head1);
	}
	
	private static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}
}
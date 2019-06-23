package question16;

import java.util.Stack;

/**
 * �������������ʱ�临�Ӷ�һ��ΪO(N)�������ڲ�ͬ�����¶Կռ临�Ӷ����Ų�ͬ��Ҫ��
 * �ж��Ƿ�Գƣ�ʹ��O(1)�Ŀռ临�Ӷȵ��㷨����ԭ���㷨��˼·��ʹ�ÿ���ָ�뽫����һ��Ϊ�����ٽ���벿�������ٱ�������
 * ע�����Ҫ������ָ�ԭ״
 * 
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
	 * need n extra space
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome1(Node head) {
		if(head == null) {
			return false;
		}
		Stack<Node> stack = new Stack<>();
		Node current = head;
		while (current != null) {
			stack.push(current);
			current = current.next;
		}	
		while (head != null) {
			if(head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	/**
	 * need n/2 extra space
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome2(Node head) {
		if(head == null) {
			return false;
		}
		if(head.next == null) {
			return true;
		}
		Node slow = head;
		Node fast = head;
		Stack<Node> stack = new Stack<>();
		stack.push(slow);
		while(fast.next != null && fast.next.next != null){
			slow = slow.next;
			stack.push(slow);
			fast = fast.next.next;
		}
		if(fast.next == null) {
			stack.pop();
		}
		slow = slow.next;
		while(slow != null) {
			if(stack.pop().value != slow.value) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}
	
	/**
	 * need O(1) extra space
	 * ʹ�ÿ���ָ�룬����벿����������Ȼ������Ƚϣ���󽫺�벿�ָֻ�
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome3(Node head) {
		if(head == null) {
			return false;
		}
		if(head.next == null) {
			return true;
		}
		Node slow = head;
		Node fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		/**
		 * Arriving here, if 1 2 3 4 3 2 1, slow points to 4 and fast points to the last 1.
		 * If 1 2 3 3 2 1, slow points to the first 3 and fast points to the last 2.
		 * Anyway, slow points to the middle of the list.
		 */
		
		//Let's start to reverse the second part.
		//Reuse the pointer fast. The pointer fast and behindFast are used to reverse the second part.
		fast = slow.next;
		slow.next = null;
		Node behindFast = slow;
		while(fast != null) {
			Node temp = fast.next;
			fast.next = behindFast;
			behindFast = fast;
			fast = temp;
		}
		//Arriving here, behindFast points to the last node and fast becomes null.
		Node lastNode = behindFast;
		//Record the last node to used in the last step.
		boolean isPalindrome = true;
		//Reuse fast to travel the first part of the list.
		fast = head;
		while(fast != null) {
			//Reuse behindFast to travel the second part of the list.
			if(fast.value != behindFast.value) {
				isPalindrome = false;
				break;
			}
			fast = fast.next;
			behindFast = behindFast.next;
		}
		/**
		 * Arriving here, the result has got, but we need to make the list what is used to be.
		 */
		
		//Reuse the behindFast. The pointer behindFast and last node is to restore the list.
		behindFast = null;
		while(lastNode != slow) {
			Node temp = lastNode.next;
			lastNode.next = behindFast;
			behindFast = lastNode;
			lastNode = temp;
		}
		//Arriving here, lastNode and slow both point to the slow node.
		slow.next = behindFast;
		return isPalindrome;
	}
	
	
	public static void main(String[] args) {
		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");
		
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
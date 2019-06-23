package question21;


/**
 * �õ����������������ǰ���ͺ��
 * @author CGWEI
 *
 */
public class Solution {
	private static class Node{
		int value;
		Node left;
		Node right;
		Node parent;
		public Node(int value) {
			this.value = value;
		}
	}
	
	/**
	 * �õ�����ĳһ�ڵ�ĺ�̽ڵ�
	 * ˼·��
	 * ����ýڵ�������������򷵻ظ�������������Ľڵ�
	 * ���û���������������ϱ�������������һ���ڵ����丸�ڵ������ʱ���õ���������ؼ��ɡ�
	 * ��ʾ�����Ի�ͼʹ˼·��������
	 * @param current
	 * @return
	 */
	public static Node getSuccessor(Node current) {
		if(current == null) {
			return null;
		}
		if(current.right != null) {
			return getLeftMostInRightChildTree(current.right);
		}
		else {
			Node parent = current.parent;
			while(parent != null) {
				if(parent.left == current) {
					break;
				}
				current = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	private static Node getLeftMostInRightChildTree(Node rightChildRoot) {
		while(rightChildRoot.left != null) {
			rightChildRoot = rightChildRoot.left;
		}
		return rightChildRoot;
	}
	
	/**
	 * �õ�һ���ڵ��ǰ���ڵ�
	 * ˼·��
	 * ����ýڵ������������򷵻ظ��������ϵ����ҽڵ�
	 * ���û���������������ϱ�������������һ���ڵ����丸�ڵ���Һ���ʱ���õ���������ؼ��ɡ�
	 * @param current
	 * @return
	 */
	public static Node getPredecessor(Node current) {
		if(current == null) {
			return current;
		}
		if(current.left != null) {
			return getRightMostInLeftChildTree(current.left);
		}
		else {
			Node parent = current.parent;
			while(parent != null) {
				if(parent.right == current) {
					break;
				}
				current = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	private static Node getRightMostInLeftChildTree(Node leftChildRoot) {
		while(leftChildRoot.right != null) {
			leftChildRoot = leftChildRoot.right;
		}
		return leftChildRoot;
	}
	
	
	
	public static void main(String[] args) {
//		Node head = new Node(6);
//		head.parent = null;
//		head.left = new Node(3);
//		head.left.parent = head;
//		head.left.left = new Node(1);
//		head.left.left.parent = head.left;
//		head.left.left.right = new Node(2);
//		head.left.left.right.parent = head.left.left;
//		head.left.right = new Node(4);
//		head.left.right.parent = head.left;
//		head.left.right.right = new Node(5);
//		head.left.right.right.parent = head.left.right;
//		head.right = new Node(9);
//		head.right.parent = head;
//		head.right.left = new Node(8);
//		head.right.left.parent = head.right;
//		head.right.left.left = new Node(7);
//		head.right.left.left.parent = head.right.left;
//		head.right.right = new Node(10);
//		head.right.right.parent = head.right;
//
//		Node test = head.left.left;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.left.left.right;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.left;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.left.right;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.left.right.right;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.right.left.left;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.right.left;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.right;
//		System.out.println(test.value + " next: " + getSuccessor(test).value);
//		test = head.right.right; // 10's next is null
//		System.out.println(test.value + " next: " + getSuccessor(test));
		
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " before: " + getPredecessor(test));
		test = head.left.left.right;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.left;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.left.right;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.right.left;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.right;
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " before: " + getPredecessor(test).value);
		
		
		
		
		
	}
	
}
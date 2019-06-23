package question24;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * �ж�һ�ö������Ƿ�Ϊ����������
 * �ж�һ�ö������Ƿ�Ϊ��ȫ������
 * @author CGWEI
 *
 */
public class Solution {
	
	private static class Node{
		int value;
		Node left;
		Node right;
		public Node(int value) {
			this.value = value;
		}
	}
	
	
	/**
	 * �ж��Ƿ�Ϊ������������ֻ��Ҫ�ж���������������Ƿ�����
	 * ע�⣺�����������ڵ�һ�㲻���
	 * @param root
	 * @return
	 */
	public static boolean isBinarySearchTree(Node root) {
		if(root == null) {
			return false;
		}
		int previous = Integer.MIN_VALUE;
		Stack<Node> nodes = new Stack<>();
		nodes.push(root);
		Node current = root.left;
		while(!nodes.isEmpty() || current != null) {
			while(current != null) {
				nodes.push(current);
				current = current.left;
			}
			current = nodes.pop();
			if(current.value < previous) {
				return false;
			}
			previous = current.value;
			System.out.println(current.value);
			current = current.right;
		}
		return true;
	}
	
	
	
	private static class ReturnValue {
		boolean isBST;
		Integer maxValue;
		Integer minValue;
		public ReturnValue(boolean b, Integer max, Integer min) {
			isBST = b;
			maxValue = max;
			minValue = min;
		}
	}
	public static boolean isBinaryTreeByRecursionEntry(Node root) {
		return isBinaryTreeByRecursion(root).isBST;
	}
	/**
	 * �ж��Ƿ����ڶ����������ĵݹ鷽��
	 * ˼·��
	 * ���ܽ��ÿһ�εݹ������Ҫ�ӵݹ���̵�ʲô���ݣ��Ǿ��ǣ��������Ƿ�Ϊ�����������������������ֵ���������Ƿ�Ϊ�����������������������ֵ
	 * �����ReturnValue��
	 * �����ÿһ�����̣����ֱ���ж������������������Ƕ�����������ֱ�ӷ���false��������Ǿ��Ƕ���������������ݲ�ͬ����ӹ���ǰ���������ֵ����Сֵ��������һ�����ء�
	 * @param root
	 * @return
	 */
	private static ReturnValue isBinaryTreeByRecursion(Node root) {
		if(root == null) {
			return new ReturnValue(true, null, null);
		}
		ReturnValue leftReturnValue = isBinaryTreeByRecursion(root.left);
		if(!leftReturnValue.isBST) {
			return new ReturnValue(false, null, null);
		}
		ReturnValue rightReturnValue = isBinaryTreeByRecursion(root.right);
		if(!rightReturnValue.isBST) {
			return new ReturnValue(false, null, null);
		}
		if(leftReturnValue.maxValue != null && rightReturnValue.minValue != null) {
			if(root.value > leftReturnValue.maxValue && root.value < rightReturnValue.minValue) {
				return new ReturnValue(true, rightReturnValue.maxValue, leftReturnValue.minValue);
			}
		}
		else if(leftReturnValue.maxValue != null) {
			if(root.value > leftReturnValue.maxValue) {
				return new ReturnValue(true, root.value, leftReturnValue.minValue);
			}
		}
		else if(rightReturnValue.maxValue != null) {
			if(root.value < rightReturnValue.minValue) {
				return new ReturnValue(true, rightReturnValue.maxValue, root.value);
			}
		}
		else {
			return new ReturnValue(true, root.value, root.value);
		}
		return new ReturnValue(false, null, null);
	}
	
	
	/**
	 * �ж϶������Ƿ�Ϊ��ȫ������
	 * ˼·��
	 * ��ʹ�ö��н��а���α���
	 * ��ÿ��ѭ���ó�һ���ڵ㣬����ýڵ����Һ��ӵ�û�����ӣ���ֱ�ӷ���false
	 * ������ó��Ľڵ��������Ӷ��У��������Ľ�����������
	 * ������ó��Ľڵ�ֻ��һ�����ӻ�û�к��ӣ������һ��״̬����״̬��ʶ��֮�����еĽڵ�ֻ����Ҷ�ӽڵ㣬���״̬������ѭ�����һ��bool������ʶ
	 * �������������״̬֮���ó��Ľڵ���Ȼ�к��ӣ��򷵻�false
	 * @param root
	 * @return
	 */
	public static boolean isCompleteBinaryTree(Node root) {
		if(root == null) {
			return false;
		}
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root);
		boolean intoLeaves = false;
		while(!nodes.isEmpty()) {
			Node temp = nodes.poll();
			if(intoLeaves) {
				if(temp.left != null || temp.right != null) {
					return false;
				}
			}
			if(temp.right != null && temp.left == null) {
				return false;
			}
			else if(temp.right != null && temp.left != null) {
				nodes.add(temp.left);
				nodes.add(temp.right);
			}
			else {
				intoLeaves = true;
				if(temp.left != null) {
					nodes.add(temp.left);
				}
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Node head = new Node(6);
		head.left = new Node(3);
		head.left.left = new Node(1);
		head.left.left.right = new Node(2);
		head.left.right = new Node(4);
		head.left.right.right = new Node(5);
		head.right = new Node(9);
		head.right.left = new Node(8);
		head.right.left.left = new Node(7);
		head.right.right = new Node(10);
		
//		//������Ϊ�˲�����ȫ����������
//		head.left.right.left = new Node(1);
//		head.left.left.left = new Node(2);
		
		isBinaryTreeByRecursionEntry(head);
		System.out.println(isBinarySearchTree(head));
		System.out.println(isBinaryTreeByRecursionEntry(head));
		System.out.println(isCompleteBinaryTree(head));

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
//		System.out.println(test.value + " before: " + getPredecessor(test));
//		test = head.left.left.right;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.left;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.left.right;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.left.right.right;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.right.left.left;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.right.left;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.right;
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
//		test = head.right.right; // 10's next is null
//		System.out.println(test.value + " before: " + getPredecessor(test).value);
		
		
		
		
		
	}
	
}
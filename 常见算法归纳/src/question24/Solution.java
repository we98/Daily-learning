package question24;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 判断一棵二叉树是否为搜索二叉树
 * 判断一棵二叉树是否为完全二叉树
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
	 * 判断是否为二叉搜索树，只需要判断中序遍历过程中是否升序
	 * 注意：二叉搜索树节点一般不相等
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
	 * 判断是否属于二叉搜索树的递归方法
	 * 思路：
	 * ①总结出每一次递归过程需要子递归过程的什么数据，那就是，左子树是否为二叉搜索树，左子树的最大值，右子树是否为二叉搜索树，右子树的最大值
	 * ②设计ReturnValue类
	 * ③针对每一个过程，如果直接判断左子树或右子树不是二叉搜索树，直接返回false，如果它们均是二叉搜索树，则根据不同情况加工当前子树的最大值和最小值，并向上一级返回。
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
	 * 判断二叉树是否为完全二叉树
	 * 思路：
	 * ①使用队列进行按层次遍历
	 * ②每次循环拿出一个节点，如果该节点右右孩子但没有左孩子，则直接返回false
	 * ③如果拿出的节点两个孩子都有，则正常的将其加入队列中
	 * ④如果拿出的节点只有一个左孩子或没有孩子，则进入一种状态，该状态标识着之后所有的节点只能是叶子节点，这个状态可以用循环外的一个bool变量标识
	 * ⑤如果进入这种状态之后拿出的节点仍然有孩子，则返回false
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
		
//		//这两行为了测试完全二叉树而加
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
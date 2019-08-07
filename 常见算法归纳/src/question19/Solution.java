package question19;

import java.util.Stack;


/**
 * 二叉树的前序中序后序遍历的递归和非递归实现
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
	 * 先序遍历
	 * 不使用递归的时候要使用栈，思路是每次从栈中取出一个，然后将这个的右孩子和左孩子依次入栈（最开始的时候将头节点入栈）
	 * @param root
	 */
	public static void preOrderByRecursion(Node root) {
		if(root == null) {
			return;
		}
		System.out.print(root.value + " ");
		preOrderByRecursion(root.left);
		preOrderByRecursion(root.right);
	}
	public static void preOrder(Node root) {
		System.out.print("pre-order: ");
		if(root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node current = stack.pop();
			System.out.print(current.value + " ");
			if(current.right != null) {
				stack.push(current.right);
			}
			if(current.left != null) {
				stack.push(current.left);
			}
		}
		System.out.println();
	}
	/**
     * 不使用栈的前序遍历，思想是将树修改为螺旋树，然后修改回原状
     * 当判断previous.right是否为空时，实则是判断当前是第一次来到这个节点还是第二次
     * previous.right为空，代表第一次来到这个节点，因此在这个条件下访问元素
     * @param root
     */
    private void preorderMorrisTraversal(TreeNode root){
        while(root != null){
            if(root.left == null){
                System.out.println(root.val);
                root = root.right;
            }
            else{
                TreeNode previous = root.left;
                while(previous.right != null && previous.right != root){
                    previous = previous.right;
                }
                if(previous.right == null){
                    System.out.println(root.val);
                    previous.right = root;
                    root = root.left;
                }
                else{
                    previous.right = null;
                    root = root.right;
                }
            }
        }
    }
	
	/**
	 * 中序遍历
	 * 不使用递归的时候要使用栈，有很多种代码编写方式，但是要注意大致思想
	 * 当获得一个节点的时候，如果这个节点不为空，则顺着这个节点一直向left孩子移动，并入栈，直到当前节点为空
	 * 当当前节点为空时，从栈中拿出一个并输出，并将当前节点设置为这个拿出节点的右孩子
	 * 重复以上两行
	 * 下面的函数在编码时，head首先入栈，所以在循环初始化时，current直接表示为root的左孩子
	 * 注意不能只将栈是否为空作为循环是否终止的判断条件，因为在向上遍历到根节点时，将根节点拿出并将current设置为根节点的右孩子这一步，栈就空了
	 * 所以在判断终止的条件中应该也加入当前节点是否为空
	 * @param root
	 */
	public static void inOrderByRecursion(Node root) {
		if(root == null) {
			return;
		}
		inOrderByRecursion(root.left);
		System.out.print(root.value + " ");
		inOrderByRecursion(root.right);
	}
	public static void inOrder(Node root) {
		System.out.print("in-order: ");
		if(root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		Node current = root.left;
		while(!stack.isEmpty() || current != null) {
			while(current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			System.out.print(current.value + " ");
			current = current.right;
		}
		System.out.println();
	}
	/**
     * 代码跟前序遍历一模一样，只不过当previous.right不为空时，代表第二次来到这个节点，因此中序访问
     * @param root
     */
    private void inorderMorrisTraversal(TreeNode root){
        while (root != null){
            if(root.left == null){
                System.out.println(root.val);
                root = root.right;
            }
            else{
                TreeNode previous = root.left;
                while (previous.right != null && previous.right != root){
                    previous = previous.right;
                }
                if(previous.right == null){
                    previous.right = root;
                    root = root.left;
                }
                else{
                    previous.right = null;
                    System.out.println(root.val);
                    root = root.right;
                }
            }
        }
    }
	
	/**
	 * 后序遍历
	 * 不使用递归的时候仍然使用栈，这里使用了一种简单的方式
	 * 前序遍历是中、左、右的顺序，根据前序遍历的思想，很容易改为中、右、左
	 * 后序遍历要求的是左、右、中，因此只需要准备一个额外的栈
	 * 改写前序遍历，在该输出的地方不输出，将弹出的节点压入额外的栈，最后将额外的栈输出即可。
	 * @param root
	 */
	public static void postOrderByRecursion(Node root) {
		if(root == null) {
			return;
		}
		postOrderByRecursion(root.left);
		postOrderByRecursion(root.right);
		System.out.print(root.value + " ");
	}
	public static void postOrder(Node root) {
		System.out.print("post-order: ");
		if(root == null) {
			return;
		}
		Stack<Node> data = new Stack<>();
		data.push(root);
		Stack<Node> help = new Stack<>();
		while(!data.isEmpty()) {
			Node current = data.pop();
			//System.out.print(current.value + " ");
			//这里不输出，而是将其放入到help栈中
			help.push(current);
			if(current.left != null) {
				data.push(current.left);
			}
			if(current.right != null) {
				data.push(current.right);
			}
		}
		while(!help.isEmpty()) {
			System.out.print(help.pop().value + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderByRecursion(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderByRecursion(head);
		System.out.println();
		System.out.print("pos-order: ");
		postOrderByRecursion(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrder(head);
		inOrder(head);
		postOrder(head);
		
	}
}
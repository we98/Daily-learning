package question19;

import java.util.Stack;


/**
 * ��������ǰ�������������ĵݹ�ͷǵݹ�ʵ��
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
	 * �������
	 * ��ʹ�õݹ��ʱ��Ҫʹ��ջ��˼·��ÿ�δ�ջ��ȡ��һ����Ȼ��������Һ��Ӻ�����������ջ���ʼ��ʱ��ͷ�ڵ���ջ��
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
     * ��ʹ��ջ��ǰ�������˼���ǽ����޸�Ϊ��������Ȼ���޸Ļ�ԭ״
     * ���ж�previous.right�Ƿ�Ϊ��ʱ��ʵ�����жϵ�ǰ�ǵ�һ����������ڵ㻹�ǵڶ���
     * previous.rightΪ�գ������һ����������ڵ㣬�������������·���Ԫ��
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
	 * �������
	 * ��ʹ�õݹ��ʱ��Ҫʹ��ջ���кܶ��ִ����д��ʽ������Ҫע�����˼��
	 * �����һ���ڵ��ʱ���������ڵ㲻Ϊ�գ���˳������ڵ�һֱ��left�����ƶ�������ջ��ֱ����ǰ�ڵ�Ϊ��
	 * ����ǰ�ڵ�Ϊ��ʱ����ջ���ó�һ���������������ǰ�ڵ�����Ϊ����ó��ڵ���Һ���
	 * �ظ���������
	 * ����ĺ����ڱ���ʱ��head������ջ��������ѭ����ʼ��ʱ��currentֱ�ӱ�ʾΪroot������
	 * ע�ⲻ��ֻ��ջ�Ƿ�Ϊ����Ϊѭ���Ƿ���ֹ���ж���������Ϊ�����ϱ��������ڵ�ʱ�������ڵ��ó�����current����Ϊ���ڵ���Һ�����һ����ջ�Ϳ���
	 * �������ж���ֹ��������Ӧ��Ҳ���뵱ǰ�ڵ��Ƿ�Ϊ��
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
     * �����ǰ�����һģһ����ֻ������previous.right��Ϊ��ʱ������ڶ�����������ڵ㣬����������
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
	 * �������
	 * ��ʹ�õݹ��ʱ����Ȼʹ��ջ������ʹ����һ�ּ򵥵ķ�ʽ
	 * ǰ��������С����ҵ�˳�򣬸���ǰ�������˼�룬�����׸�Ϊ�С��ҡ���
	 * �������Ҫ��������ҡ��У����ֻ��Ҫ׼��һ�������ջ
	 * ��дǰ��������ڸ�����ĵط���������������Ľڵ�ѹ������ջ����󽫶����ջ������ɡ�
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
			//���ﲻ��������ǽ�����뵽helpջ��
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
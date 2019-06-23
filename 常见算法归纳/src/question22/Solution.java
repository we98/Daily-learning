package question22;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化，这里使用了前序和按层次序列化和反序列化
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
	 * 前序遍历的方式序列化二叉树
	 * @param root
	 * @return
	 */
	public static String serializedByPreOrder(Node root) {
		if(root == null) {
			return "#";
		}
		StringBuilder stringBuilder = new StringBuilder(String.valueOf(root.value));
		stringBuilder.append('_');
		stringBuilder.append(serializedByPreOrder(root.left));
		stringBuilder.append('_');
		stringBuilder.append(serializedByPreOrder(root.right));
		return stringBuilder.toString();
	}
	
	/**
	 * 前序遍历的方式反序列化
	 * @param serializedTree
	 * @return
	 */
	public static Node deserializedByPreOrder(String serializedTree) {
		if(serializedTree == null || !serializedTree.matches("([(\\d+)#]_)+#")) {
			return null;
		}
		String[] nodes = serializedTree.split("_");
		Queue<String> queue = new LinkedList<>();
		for(String s : nodes) {
			queue.add(s);
		}
		return deserializedByPreOrder(queue);
	}
	private static Node deserializedByPreOrder(Queue<String> nodes) {
		String nodeValue = nodes.poll();
		if(nodeValue.equals("#")) {
			return null;
		}
		Node root = new Node(Integer.valueOf(nodeValue));
		root.left = deserializedByPreOrder(nodes);
		root.right = deserializedByPreOrder(nodes);
		return root;
	}
	
	/**
	 * 层次遍历的方式序列化
	 * 步骤：
	 * 使用队列，每次在循环中取出一个节点，如果该节点取出的节点不为空，将其左孩子和右孩子都加入队列（无论是否为空都加），并打印节点的value
	 * 如果取出的为空，则打印#
	 * 注意，这个问题和一般的层次遍历不一样，一般的层次遍历不在队列里放空指针，而这个放空指针
	 * 循环退出条件为队列为空，因此注意将root节点首先加入到队列中去
	 * @param root
	 * @return
	 */
	public static String serializedByLevelOrder(Node root) {
		if(root == null) {
			return null;
		}
		//Reuse root.
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root);
		StringBuilder stringBuilder = new StringBuilder();
		while(!nodes.isEmpty()) {
			root = nodes.poll();
			if(root == null) {
				stringBuilder.append("#_");
			}
			else {
				stringBuilder.append(String.valueOf(root.value));
				stringBuilder.append('_');
				nodes.add(root.left);
				nodes.add(root.right);
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}
	
	/**
	 * 层次遍历的方式反序列化
	 * 步骤：
	 * 在每个循环之中，取出队列中的节点，并访问string数组的下两位，生成相应的节点，如果不为空，继续放到队列中去，如果为空，不放
	 * 注意在遍历数组时，index每次循环走两步
	 * 这个和序列化不同，序列化的时候无论是否为空，都要加入到队列中去，而这个只加非空
	 * 画图有助于思考
	 * @param serializedTree
	 * @return
	 */
	public static Node deserializedByLevelOrder(String serializedTree) {
		if(serializedTree == null || !serializedTree.matches("([(\\d+)#]_)+#")) {
			return null;
		}
		String[] nodes = serializedTree.split("_");
		Queue<Node> help = new LinkedList<>();
		Node root = new Node(Integer.valueOf(nodes[0]));
		help.add(root);
		int index = 1;
		while(index < nodes.length) {
			Node temp = help.poll();
			if(!nodes[index].equals("#")) {
				temp.left = new Node(Integer.valueOf(nodes[index++]));
				help.add(temp.left);
			}
			if(!nodes[index].equals("#")) {
				temp.right = new Node(Integer.valueOf(nodes[index++]));
				help.add(temp.right);
			}
		}
		return root;
	}
	
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.right = new Node(6);
		root.left.right.right = new Node(7);
		printTree(root);
		String s1 = serializedByPreOrder(root);
		Node root2 = deserializedByPreOrder(s1);
		printTree(root2);
		
		String s2 = serializedByLevelOrder(root2);
		printTree(deserializedByLevelOrder(s2));
	}
	
	
	/**
	 * 打印二叉树的函数
	 * @param head
	 */
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}
	
}
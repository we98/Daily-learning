package question22;

import java.util.LinkedList;
import java.util.Queue;

/**
 * �����������л��ͷ����л�������ʹ����ǰ��Ͱ�������л��ͷ����л�
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
	 * ǰ������ķ�ʽ���л�������
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
	 * ǰ������ķ�ʽ�����л�
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
	 * ��α����ķ�ʽ���л�
	 * ���裺
	 * ʹ�ö��У�ÿ����ѭ����ȡ��һ���ڵ㣬����ýڵ�ȡ���Ľڵ㲻Ϊ�գ��������Ӻ��Һ��Ӷ�������У������Ƿ�Ϊ�ն��ӣ�������ӡ�ڵ��value
	 * ���ȡ����Ϊ�գ����ӡ#
	 * ע�⣬��������һ��Ĳ�α�����һ����һ��Ĳ�α������ڶ�����ſ�ָ�룬������ſ�ָ��
	 * ѭ���˳�����Ϊ����Ϊ�գ����ע�⽫root�ڵ����ȼ��뵽������ȥ
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
	 * ��α����ķ�ʽ�����л�
	 * ���裺
	 * ��ÿ��ѭ��֮�У�ȡ�������еĽڵ㣬������string���������λ��������Ӧ�Ľڵ㣬�����Ϊ�գ������ŵ�������ȥ�����Ϊ�գ�����
	 * ע���ڱ�������ʱ��indexÿ��ѭ��������
	 * ��������л���ͬ�����л���ʱ�������Ƿ�Ϊ�գ���Ҫ���뵽������ȥ�������ֻ�ӷǿ�
	 * ��ͼ������˼��
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
	 * ��ӡ�������ĺ���
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
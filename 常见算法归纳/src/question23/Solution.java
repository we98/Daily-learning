package question23;



/**
 * �������ĸ߶�
 * �������Ƿ�ƽ��
 * ͨ������������������Ҫ���������д�������ݹ麯����һ��˼·
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
	 * �õ��������߶�
	 * ����˼·��
	 * �ٵ�ǰ�ݹ麯����Ҫ����Ϣ���������ĸ߶Ⱥ��������ĸ߶�
	 * ����Ʒ���ֵ����Ϊ��Ҫ���Ǹ߶ȶ�����Ҫ������Ϣ�����Է���ֵΪint
	 * ����ƺ����ӹ����̣��õ��������߶Ⱥ��������߶Ⱥ󣬽����ֵ��һ���ɡ�
	 * @param root
	 * @return
	 */
	public static int height(Node root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	
	
	
	public static class ReturnValue{
		boolean isBalanced;
		int height;
		public ReturnValue(boolean b, int h) {
			isBalanced = b;
			height = h;
		}
	}
	public static boolean isBalanced(Node root) {
		//return process(root).isBalanced;
		return getHeight(root) == -1 ? false : true;
	}
	/**
	 * ˼·��
	 * �ٵ����еݹ�ʱ����ǰ�ݹ���Ҫ�õ�����Ϣ�ǣ��������Ƿ�ƽ�⣬�������Ƿ�ƽ�⣬�Լ����������������ĸ߶ȡ�
	 * �������������һ����ƽ�⣬ֱ�ӷ��ز�ƽ�⡣�����ƽ�⣬�Ƚϸ߶ȡ�
	 * �ڸ��������˼�룬����֪��ÿһ���ݹ���̵ķ���ֵ���Ƿ�ƽ��͵�ǰ�����ĸ߶�����������
	 * �۵õ�����ֵ�󣬽��мӹ��������һ����ƽ�⣬ֱ�ӷ��ز�ƽ�⡣�����ƽ�⣬�Ƚϸ߶ȣ��߶Ȳ����1�����ز�ƽ�⣬�����������������ṩ����Ϣ���ӹ��Լ�����Ϣ�����ء�
	 * @param root
	 * @return
	 */
	private static ReturnValue process(Node root) {
		if(root == null) {
			return new ReturnValue(true, 0);
		}
		ReturnValue leftReturnValue = process(root.left);
		if(!leftReturnValue.isBalanced) {
			//���ﲻ�ı�߶ȵ�ԭ������Ϊ���Ѿ�����ĳ��������ƽ��ʱ���ı�߶��Ѿ�û�������ˣ���Ϊ����ĵݹ���̶�������������߶�
			return new ReturnValue(false, 0);
		}
		ReturnValue rightReturnValue = process(root.right);
		if(!rightReturnValue.isBalanced) {
			return new ReturnValue(false, 0);
		}
		//������˵������������ƽ�⣬�������ж����������ĸ߶Ȳ��Ƿ����1
		if(Math.abs(leftReturnValue.height-rightReturnValue.height) > 1) {
			return new ReturnValue(false, 0);
		}
		//������˵����ǰ�ڵ�Ϊ���ڵ������������ƽ�⣬����true��ע����һ��Ҫ�ӹ��Լ��ĸ߶�
		return new ReturnValue(true, Math.max(leftReturnValue.height, rightReturnValue.height)+1);
	}
	/**
	 * ����һ���ж��Ƿ�ƽ��Ľⷨ����������ƽ��ʱ������-1�������һ������˼��һ�£�ֻ������һ����������false��ʱ��height�����Ͳ���ʹ���ˣ�
	 * �������������-1�����Ѿ���ƽ����
	 * @param root
	 * @return
	 */
	public static int getHeight(Node root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = getHeight(root.left);
		if(leftHeight == -1) {
			return -1;
		}
		int rightHeight = getHeight(root.right);
		if(rightHeight == -1) {
			return -1;
		}
		if(Math.abs(leftHeight-rightHeight)>1) {
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
//		root.right = new Node(3);
		root.left.left = new Node(4);
//		root.left.right = new Node(5);
//		root.right.right = new Node(6);
//		root.left.right.right = new Node(7);
		System.out.println(height(root));
		System.out.println(isBalanced(root));
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
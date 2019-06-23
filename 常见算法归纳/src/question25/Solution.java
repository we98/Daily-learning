package question25;


/**
 * �ݹ�����ȫ�������ڵ����
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
	 * �õ���ȫ�������Ľڵ����
	 * ˼·��
	 * ���õ�һ���ڵ㣬�ȵõ�����ڵ����������������ĸ߶ȣ�ֱ�Ӵ�rootһ·����Ϳ��Եõ��߶ȣ�
	 * ��������������߶���ͬ����˵����ǰ�ڵ����������������������ǰ�ڵ�+��ǰ�ڵ�����������������2���������߶ȵ�ƽ���������������ݹ��������������Ϊ������������ȫ����������ԭ�����Ч
	 * ������������߶ȱ��������߶�С1����˵����������������������ǰ�ڵ�+��ǰ�ڵ�����������������2���������߶ȵ�ƽ���������������ݹ������������
	 * @param root
	 * @return
	 */
	public static int getNodeNumber(Node root) {
		if(root == null) {
			return 0;
		}
		int leftChildHeight = getHeight(root.left);
		int rightChildHeight = getHeight(root.right);
		//rightChildHeight����leftChildHeight��leftChildHeight-1
		if(leftChildHeight == rightChildHeight) {
			return (1 << leftChildHeight) + getNodeNumber(root.right);
		}
		else {
			return (1 << rightChildHeight) + getNodeNumber(root.left);
		}
	}
	private static int getHeight(Node root) {
		int height = 0;
		while(root != null) {
			height++;
			root = root.left;
		}
		return height;
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
		
		//������Ϊ�˲�����ȫ����������
		head.left.right.left = new Node(1);
		head.left.left.left = new Node(2);
		
		System.out.println(getNodeNumber(head));
	}
	
}
package question25;


/**
 * 递归求完全二叉树节点个数
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
	 * 得到完全二叉树的节点个数
	 * 思路：
	 * ①拿到一个节点，先得到这个节点左子树和右子树的高度（直接从root一路向左就可以得到高度）
	 * ②如果两棵子树高度相同，则说明当前节点的左子树是满二叉树，则当前节点+当前节点左子树的数量等于2的左子树高度的平方，接下来继续递归遍历右子树，因为右子树属于完全二叉树，跟原问题等效
	 * ③如果右子树高度比左子树高度小1，则说明右子树是满二叉树，则当前节点+当前节点右子树的数量等于2的右子树高度的平方，接下来继续递归遍历左子树。
	 * @param root
	 * @return
	 */
	public static int getNodeNumber(Node root) {
		if(root == null) {
			return 0;
		}
		int leftChildHeight = getHeight(root.left);
		int rightChildHeight = getHeight(root.right);
		//rightChildHeight等于leftChildHeight或leftChildHeight-1
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
		
		//这两行为了测试完全二叉树而加
		head.left.right.left = new Node(1);
		head.left.left.left = new Node(2);
		
		System.out.println(getNodeNumber(head));
	}
	
}
package question23;



/**
 * 二叉树的高度
 * 二叉树是否平衡
 * 通过这两个函数，最重要的是整理出写二叉树递归函数的一般思路
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
	 * 得到二叉树高度
	 * 整理思路：
	 * ①当前递归函数需要的信息是左子树的高度和右子树的高度
	 * ②设计返回值，因为需要的是高度而不需要其他信息，所以返回值为int
	 * ③设计函数加工过程，得到左子树高度和右子树高度后，将最大值加一即可。
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
	 * 思路：
	 * ①当进行递归时，当前递归需要得到的信息是：左子树是否平衡，右子树是否平衡，以及左子树和右子树的高度。
	 * 如果左右子树有一个不平衡，直接返回不平衡。如果都平衡，比较高度。
	 * ②根据上面的思想，可以知道每一个递归过程的返回值是是否平衡和当前子树的高度这两个变量
	 * ③得到返回值后，进行加工，如果有一个不平衡，直接返回不平衡。如果都平衡，比较高度，高度差大于1，返回不平衡，否则，利用左右子树提供的信息，加工自己的信息并返回。
	 * @param root
	 * @return
	 */
	private static ReturnValue process(Node root) {
		if(root == null) {
			return new ReturnValue(true, 0);
		}
		ReturnValue leftReturnValue = process(root.left);
		if(!leftReturnValue.isBalanced) {
			//这里不改变高度的原因是因为当已经发现某科子树不平衡时，改变高度已经没有意义了，因为后序的递归过程都不会利用这个高度
			return new ReturnValue(false, 0);
		}
		ReturnValue rightReturnValue = process(root.right);
		if(!rightReturnValue.isBalanced) {
			return new ReturnValue(false, 0);
		}
		//到这里说明两棵子树都平衡，接下来判断两棵子树的高度差是否大于1
		if(Math.abs(leftReturnValue.height-rightReturnValue.height) > 1) {
			return new ReturnValue(false, 0);
		}
		//到这里说明当前节点为根节点的整个子树都平衡，返回true，注意这一步要加工自己的高度
		return new ReturnValue(true, Math.max(leftReturnValue.height, rightReturnValue.height)+1);
	}
	/**
	 * 另外一种判断是否平衡的解法，当子树不平衡时，返回-1，这跟上一个方法思想一致，只不过上一个方法返回false的时候height变量就不再使用了，
	 * 而这个函数返回-1代表已经不平衡了
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
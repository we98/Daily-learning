package question26;

/**
 * 二叉树折纸问题
 * @author CGWEI
 *
 */
public class Solution {
	
	/**
	 * 折纸问题
	 * 思路：
	 * 将折纸问题看作二叉树的中序遍历
	 * 注意因为没有实际的二叉树结构，所以判断左右子树时，应该再加一个boolean变量
	 * 因为树形结构为完全二叉树结构，所以可以根据下标来确定边界条件
	 * @param n
	 */
	public static void paperFolding(int n) {
		if(n > 0) {
			int total = (1 << n) - 1;
			paperFolding(0, total, true);
			System.out.println();
		}
		//注释着的方法更简洁，但是不容易理解
//		if(n > 0) {
//			int i = 1;
//			paperFolding(i, n, true);
//			System.out.println();
//		}
	}
	private static void paperFolding(int current, int total, boolean isLeft) {
		if(current < total) {
			paperFolding((current << 1) + 1, total, true);
			System.out.print(isLeft ? "down " : "up ");
			paperFolding((current << 1) + 2, total, false);
		}
//		if(current <= total) {
//			paperFolding(current + 1, total, true);
//			System.out.print(isLeft ? "down " : "up ");
//			paperFolding(current + 1, total, false);
//		}
	}
	
	
	
	public static void main(String[] args) {
		paperFolding(4);
	}
	
}
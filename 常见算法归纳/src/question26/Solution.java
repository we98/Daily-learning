package question26;

/**
 * ��������ֽ����
 * @author CGWEI
 *
 */
public class Solution {
	
	/**
	 * ��ֽ����
	 * ˼·��
	 * ����ֽ���⿴�����������������
	 * ע����Ϊû��ʵ�ʵĶ������ṹ�������ж���������ʱ��Ӧ���ټ�һ��boolean����
	 * ��Ϊ���νṹΪ��ȫ�������ṹ�����Կ��Ը����±���ȷ���߽�����
	 * @param n
	 */
	public static void paperFolding(int n) {
		if(n > 0) {
			int total = (1 << n) - 1;
			paperFolding(0, total, true);
			System.out.println();
		}
		//ע���ŵķ�������࣬���ǲ��������
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
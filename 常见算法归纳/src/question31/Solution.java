package question31;

import java.util.Arrays;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	/**
	 * �����ݹ����
	 * @param array
	 * @return
	 */
	public static int getMinPath(int[][] array) {
		if(array == null) {
			return 0;
		}
		return getMinPath(array, 0, 0);
	}
	/**
	 * ��i��jλ���ϵ����½ǵ���С·����
	 * ��ǰλ��λ�����һ�л����һ��ʱ����С·������ʵ�Ѿ�ȷ����
	 * �������ʱ���ݹ����
	 * @param array
	 * @param i
	 * @param j
	 * @return
	 */
	private static int getMinPath(int[][] array, int i, int j) {
		if(i == array.length-1 && j == array[0].length-1) {
			return array[i][j];
		}
		else if(i == array.length - 1) {
			int sum = 0;
			while(j < array[0].length) {
				sum += array[i][j++];
			}
			return sum;
		}
		else if(j == array[0].length - 1) {
			int sum = 0;
			while(i < array.length) {
				sum += array[i++][j];
			}
			return sum;
		}
		else {
			return Math.min(getMinPath(array, i + 1, j), getMinPath(array, i, j + 1)) + array[i][j];
		}
	}
	
	
	
	/**
	 * ��̬�滮
	 * �����������ϴ��ľ�Ĺ���
	 * @param array
	 * @return
	 */
	public static int getMinPath2(int[][] array) {
		if(array == null || array.length < 1 || array[0] == null || array[0].length < 1) {
			return 0;
		}
		int totalRow = array.length;
		int totalColumn = array[0].length;
		int[][] dp = new int[totalRow][totalColumn];
		dp[totalRow-1][totalColumn-1] = array[totalRow-1][totalColumn-1];
		for(int j = totalColumn-2; j >= 0; j--) {
			dp[totalRow-1][j] = array[totalRow-1][j];
			dp[totalRow-1][j] += dp[totalRow-1][j+1];
		}
		for(int i = totalRow-2; i >= 0; i--) {
			dp[i][totalColumn-1] = array[i][totalColumn-1];
			dp[i][totalColumn-1] += dp[i+1][totalColumn-1];
		}
		for(int i = totalRow-2; i >= 0; i--) {
			for(int j = totalColumn-2; j >= 0; j--) {
				dp[i][j] = array[i][j];
				dp[i][j] += Math.min(dp[i + 1][j], dp[i][j + 1]);
			}
		}
		for(int i = 0; i < dp.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		return dp[0][0];
		
	}
	
	public static void main(String[] args) {
		int[][] array = {
				{2, 2, 3, 0},
				{3, 4, 9, 2},
				{0, 4, 1, 5},
				{0, 9, 2, 7}
		};
		System.out.println(getMinPath(array));
		System.out.println(getMinPath2(array));
	}
	
}
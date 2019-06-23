package question32;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	
	public static boolean isSum(int[] array, int sum) {
		if(array == null || array.length < 1) {
			return false;
		}
		return isSum(array, 0, 0, sum);
	}
	/**
	 * ������˼������ַ�����������˼��һ��
	 * @param array
	 * @param index
	 * @param current
	 * @param sum
	 * @return
	 */
	private static boolean isSum(int[] array, int index, int current, int sum) {
		if(current == sum) {
			return true;
		}
		if(index == array.length) {
			return current == sum;
			//return false;
		}
		return isSum(array, index + 1, current + array[index], sum) || isSum(array, index + 1, current, sum);
	}
	
	
	
	public static boolean isSum2(int[] array, int sum) {
		if(array == null || array.length < 1) {
			return false;
		}
		int maxValue = 0;
		int minValue = Integer.MAX_VALUE;
		for(int i = 0; i < array.length; i++) {
			minValue = minValue > array[i] ? array[i] : minValue;
			maxValue += array[i] > 0 ? array[i] : 0;
		}
		if(sum < minValue || sum > maxValue) {
			return false;
		}
		int totalColumn = maxValue - minValue + 1;
		boolean[][] flags = new boolean[array.length][totalColumn];
		//���һ��
		for(int j = 0; j < totalColumn; j++) {
			flags[0][j] = array[0] == j+minValue ? true : false; 
			if(array[0] == sum) {
				return true;
			}
		}
		/**
		 * ��ʣ�¼���
		 * ���ȣ���array[i]��Ӧ��ֵ���ڵ�����Ϊtrue
		 * Ȼ�󣬵�ǰλ����һ�е���ͬλ��Ϊtrue�Ļ�������ǰλ����Ϊtrue
		 * ͬʱ������һ������Ϊtrue��λ�ü���array[i]������Ӧ����Ҳ��Ϊtrue
		 */
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < totalColumn; j++) {
				flags[i][array[i]-minValue] = true;
				if(flags[i-1][j]) {
					flags[i][j] = true;
					flags[i][j+array[i]] = true;
					if(sum == j+array[i]+minValue) {
						return true;
					}
				}
			}
		}
		
//		for(int i = 0; i < flags.length; i++) {
//			System.out.println(Arrays.toString(flags[i]));
//		}
		return flags[array.length-1][sum-minValue];
	}
	
	public static void main(String[] args) {
		int[] array = {
				-1, 1, 2, 3, 4,2,0,17
		};
		int[] temp = getMinValueAndSum(array);
		for(int i = temp[0]; i < temp[1]; i++) {
			if(isSum(array, i) != isSum2(array, i)) {
				System.out.println("error");
			}
		}
		System.out.println("yeah");
		
//		System.out.println(isSum(array, 2));
//		System.out.println(isSum2(array, 2));
	}
	
	
	private static int[] getMinValueAndSum(int[] array) {
		int minValue = Integer.MAX_VALUE;
		int allSum = 0;
		for(int i = 0; i < array.length; i++) {
			minValue = minValue > array[i] ? array[i] : minValue;
			allSum += array[i];
		}
		return new int[] {minValue, allSum};
	}
}
package question27;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 将一个字符串数组拼接，求任意拼接后字典序最小的序列
 * 思路：
 * 这种贪心问题首先确定贪心策略
 * 这个问题的贪心策略是：如果a+b的字典序小于b+a的字典序，则将a调整到b前面，而不是a的字典序小于b的字典序就将a调整到b前面
 * @author CGWEI
 *
 */
public class Solution {
	
	private static class MyComparator implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return (o1+o2).compareTo(o2+o1);
		}
	}
	public static String getMinString(String[] array) {
		if(array == null || array.length < 1) {
			return "";
		}
		Arrays.sort(array, new MyComparator());
		StringBuilder stringBuilder = new StringBuilder(array[0]);
		for(int i = 1; i < array.length; i++) {
			stringBuilder.append(array[i]);
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		
		String[] array = {"dwj", "caig", "b", "ba"};
		System.out.println(getMinString(array));
		
	}
	
}
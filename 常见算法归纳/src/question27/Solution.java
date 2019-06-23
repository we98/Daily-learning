package question27;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ��һ���ַ�������ƴ�ӣ�������ƴ�Ӻ��ֵ�����С������
 * ˼·��
 * ����̰����������ȷ��̰�Ĳ���
 * ��������̰�Ĳ����ǣ����a+b���ֵ���С��b+a���ֵ�����a������bǰ�棬������a���ֵ���С��b���ֵ���ͽ�a������bǰ��
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
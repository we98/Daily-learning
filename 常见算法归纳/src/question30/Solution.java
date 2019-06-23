package question30;

import java.util.Stack;

/**
 * 
 * ��ŵ������
 * ��ӡ�ַ�������������
 * ��ӡ�ַ�����ȫ����
 * 
 * @author CGWEI
 *
 */


public class Solution {
	
	/**
	 * ��ŵ��
	 * @param n
	 */
	public static void hanio(int n) {
		if(n > 0) {
			hanio(n, "left", "right", "middle");
		}
	}
	/**
	 * n����1-n�㣬��1-n�㿴��һ�����壬ֻ��Ҫ�ƶ�1-n���n+1�㼴��
	 * @param n
	 * @param from
	 * @param to
	 * @param help
	 */
	private static void hanio(int n, String from, String to, String help) {
		if(n == 1) {
			System.out.println("Plate 1 transfers from " + from + " to " + to);
			return;
		}
		hanio(n - 1, from, help, to);
		System.out.println("Plate " + n + " transfers from " + from + " to " + to);
		hanio(n - 1, help, to, from);
	}
	
	
	/**
	 * ������
	 * @param string
	 */
	public static void printAllSubSequenceOfString(String string) {
		if(string == null || string.length() < 1) {
			return;
		}
		printAllSubSequenceOfString(string.toCharArray(), 0, "");
	}
	/**
	 * ���ÿһ���ַ�������ѡ���������Ҳ����ѡ�񲻼�������������ĩβʱ����������
	 * @param array
	 * @param index
	 * @param current
	 */
	private static void printAllSubSequenceOfString(char[] array, int index, String current) {
		if(index == array.length) {
			System.out.println(current);
			return;
		}
		printAllSubSequenceOfString(array, index + 1, current);
		printAllSubSequenceOfString(array, index + 1, current + String.valueOf(array[index]));
	}
	
	
	/**
	 * ȫ����
	 * @param s
	 */
	public static void printAllPermutationsOfString(String s) {
		if(s == null || s.length() < 1) {
			return;
		}
		printAllPermutationsOfString(s.toCharArray(), 0, s.length() - 1);
	}
	/**
	 * ÿ��������ߵĻ�
	 * 1234->1234->2134->1234->3214->1234->4231->1234
	 * ��ס����֮��Ҫ������
	 * @param array
	 * @param left
	 * @param right
	 */
	private static void printAllPermutationsOfString(char[] array, int left, int right) {
		if(left == right) {
			System.out.println(String.valueOf(array));
			return;
		}
		for(int i = left; i <= right; i++) {
			swap(array, left, i);
			printAllPermutationsOfString(array, left+1, right);
			swap(array, i, left);
		}
	}
	private static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}	
	
	
	/**
	 * ��ӡ�����Ӵ�
	 * @param s
	 */
	private static void printAllSubStringOfString(String s) {
		if(s == null || s.length() < 1) {
			return;
		}
		printAllSubStringOfString(s, "", 0);
	}
	/**
	 * ˼�룺
	 * ���������
	 * �ٵ�ǰ�Ӵ����Ȳ�Ϊ0ʱ��������ѡ��ֱ�Ӵ�ӡ�����ݹ顢�������¼��ַ�
	 * �ڵ�ǰ�Ӵ�����Ϊ0ʱ��������ѡ�񣺼������¼��ַ����������²����ַ�
	 * ��ֹ����Ϊ�±굽ĩβ
	 * @param s
	 * @param current
	 * @param index
	 */
	private static void printAllSubStringOfString(String s, String current, int index) {
		if(index == s.length()) {
			System.out.println(current);
			return;
		}
		if(current == "") {
			printAllSubStringOfString(s, "", index + 1);
		}
		else {
			System.out.println(current);
		}
		printAllSubStringOfString(s, current+(s.charAt(index)), index + 1);
	}
	
	public static void main(String[] args) {
		hanio(3);
		String s = "abcd";
		System.out.println("=====================");
		printAllSubSequenceOfString(s);
		System.out.println("=====================");
		printAllPermutationsOfString(s);
		System.out.println("=====================");
		printAllSubStringOfString(s);
		System.out.println("=====================");
		int[] array = {1,2,3,4};
	}
}
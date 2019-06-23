package question30;

import java.util.Stack;

/**
 * 
 * 汉诺塔问题
 * 打印字符串子序列问题
 * 打印字符串的全排列
 * 
 * @author CGWEI
 *
 */


public class Solution {
	
	/**
	 * 汉诺塔
	 * @param n
	 */
	public static void hanio(int n) {
		if(n > 0) {
			hanio(n, "left", "right", "middle");
		}
	}
	/**
	 * n代表1-n层，将1-n层看作一个整体，只需要移动1-n层和n+1层即可
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
	 * 子序列
	 * @param string
	 */
	public static void printAllSubSequenceOfString(String string) {
		if(string == null || string.length() < 1) {
			return;
		}
		printAllSubSequenceOfString(string.toCharArray(), 0, "");
	}
	/**
	 * 针对每一个字符，可以选择加上它，也可以选择不加它，当索引到末尾时函数结束。
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
	 * 全排列
	 * @param s
	 */
	public static void printAllPermutationsOfString(String s) {
		if(s == null || s.length() < 1) {
			return;
		}
		printAllPermutationsOfString(s.toCharArray(), 0, s.length() - 1);
	}
	/**
	 * 每次与最左边的换
	 * 1234->1234->2134->1234->3214->1234->4231->1234
	 * 记住换完之后要换回来
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
	 * 打印所有子串
	 * @param s
	 */
	private static void printAllSubStringOfString(String s) {
		if(s == null || s.length() < 1) {
			return;
		}
		printAllSubStringOfString(s, "", 0);
	}
	/**
	 * 思想：
	 * 两种情况：
	 * ①当前子串长度不为0时，有两种选择：直接打印结束递归、继续向下加字符
	 * ②当前子串长度为0时，有两种选择：继续向下加字符，继续向下不加字符
	 * 终止条件为下标到末尾
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
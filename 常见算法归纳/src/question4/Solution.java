package question4;

/**
 * 字符串转数字
 * 
 * 微软面试的题目，除了实现基本的功能外，注意错误情况和特殊条件，如+-号，还有错误条件的处理等等。
 * 思路：考虑最一般的情况，如123，当读取字符串到达12和3之间时，应该怎样获得123，很明显是12*10+3，除此之外，
 * 代码还考虑了是否是合法数字，有符号、是否越界等问题。
 * 
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.convertStringToInt("1111111111"));
	}
	public int convertStringToInt(String s) {
		if(s == null) {
			throw new NullPointerException("Argument is null.");
		}
		if(!check(s)) {
			throw new NumberFormatException("Wrong format.");
		}
		char firstChar = s.charAt(0);
		int initialValue = firstChar=='+'||firstChar=='-' ? 1 : 0;
		int result = 0;
		for(int i = initialValue; i < s.length(); i++) {
			
			result = result * 10 + (int)(s.charAt(i)-'0');
			
			if(firstChar == '-') {
				if(result > 0) {
					throw new NumberFormatException("Out of range");
				}
			}
			else {
				if(result < 0) {
					throw new NumberFormatException("Out of range");
				}
			}
		}
		if(firstChar=='-') {
			result = -result;
		}
		return firstChar=='-' ? -result : result;
	}
	private boolean check(String s) {
		return s.matches("[\\+-]?\\d+");
	}
}
package question4;

/**
 * �ַ���ת����
 * 
 * ΢�����Ե���Ŀ������ʵ�ֻ����Ĺ����⣬ע����������������������+-�ţ����д��������Ĵ���ȵȡ�
 * ˼·��������һ����������123������ȡ�ַ�������12��3֮��ʱ��Ӧ���������123����������12*10+3������֮�⣬
 * ���뻹�������Ƿ��ǺϷ����֣��з��š��Ƿ�Խ������⡣
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
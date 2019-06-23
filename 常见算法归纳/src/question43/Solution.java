package question42;

import java.util.Arrays;

/**
 * 
 * @author CGWEI
 *
 */
public class Solution{
	public static void main(String[] args) {
        System.out.println(multiply("9125", "9258"));
    }
    public static String multiply(String num1, String num2) {
        byte[] finalResult = new byte[num1.length() + num2.length()];
        int bias = 0;
        for(int i = num2.length() - 1; i > -1; --i){
            byte[] result = multipleCharWithString(num1, num2.charAt(i));
            add(finalResult, result, bias++);
        }
        StringBuilder stringBuilder = new StringBuilder(num1.length() + num2.length());
        boolean leadingZero = true;
        for(int i = 0; i < finalResult.length; ++i){
            if(finalResult[i] == 0 && leadingZero){
                continue;
            }
            leadingZero = false;
            stringBuilder.append((char)(finalResult[i] + '0'));
        }
        return stringBuilder.toString();
    }
    private static byte[] multipleCharWithString(String s, char c) {
        int cValue = c - '0';
        byte[] result = new byte[s.length() + 1];
        byte carry = 0;
        for(int i = s.length() - 1; i > -1; --i) {
            char current = s.charAt(i);
            int multipleResult = (current - '0') * cValue + carry;
            result[i + 1] = (byte) (multipleResult % 10);
            carry = (byte) (multipleResult / 10);
        }
        if(carry != 0) {
            result[0] = carry;
        }
        return result;
    }
    private static void add(byte[] finalResult, byte[] result, int bias){
        int resultIndex = result.length - 1;
        int loopEnd = finalResult.length - 1 - bias - result.length;
        byte carry = 0;
        for(int i = finalResult.length - 1 - bias; i > loopEnd; --i){
            int temp = result[resultIndex--] + finalResult[i] + carry;
            finalResult[i] = (byte) (temp % 10);
            carry = (byte) (temp / 10);
        }
        if(carry != 0){
            finalResult[loopEnd] = carry;
        }
    }

    //这个方法更简洁
    public static String multiply2(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        int m = num1.length(), n = num2.length();
        byte[] finalResult = new byte[m + n];
        for(int i = m - 1; i > -1; --i){
            for(int j = n - 1; j > -1; --j){
                byte value = (byte) ((num1.charAt(i) - '0') * (num2.charAt(j) - '0'));
                int p1 = i + j;
                int p2 = i + j + 1;
                byte sum = (byte) (finalResult[p2] + value);
                //实际计算的位
                finalResult[p2] = (byte) (sum % 10);
                //进位
                finalResult[p1] += (byte) (sum / 10);
            }
        }
        StringBuilder stringBuilder = new StringBuilder(m+n);
        for(int i = 0; i < finalResult.length; ++i){
            if(stringBuilder.length() != 0 || finalResult[i] != 0){
                stringBuilder.append((char)(finalResult[i] + '0'));
            }
        }
        return stringBuilder.toString();
    }
}

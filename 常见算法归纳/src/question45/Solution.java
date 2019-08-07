import java.util.*;

public class Solution{
    public static void main(String[] args) {
        System.out.println(Integer.toOctalString(218353958));
        System.out.println(transferNumberSystem("218353958", 10, 8));
    }

    /**
     * 仅支持10进制以下相互转换
     * @param originNumberString
     * @param originNumberSystem
     * @return
     */
    private static String transferNumberSystem(String originNumberString, int originNumberSystem, int targetNumberSystem){
        int originNumber = 0;
        int multiplier = 1;
        for(int i = originNumberString.length() - 1; i > -1; --i){
            int currentBit = originNumberString.charAt(i) - '0';
            originNumber += (currentBit * multiplier);
            multiplier *= originNumberSystem;
        }
        System.out.println("actuall: " + originNumber);
        Stack<Integer> help = new Stack<>();
        while (originNumber != 0){
            help.push(originNumber % targetNumberSystem);
            originNumber /= targetNumberSystem;
        }
        StringBuilder result = new StringBuilder(help.size());
        while (!help.isEmpty()){
            result.append((char)(help.pop() + '0'));
        }
        return result.toString();
    }

}
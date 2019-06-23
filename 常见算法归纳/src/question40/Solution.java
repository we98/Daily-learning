package question40;
import java.util.Random;

/**
 * 给定原文本和模式文本，求出原文本中符合模式文本的最大左匹配
 * @author CGWEI
 *
 */
public class Solution{
    public static void main(String[] args) {
        test();
        //System.out.println(getMostMatch3("91114114","123"));
    }
    /**
     * 三种方法依次效率增加
     */
    private static void test(){
        boolean right = true;
        double time1 = 0;
        double time2 = 0;
        for(int i = 0; i < 10000; i++){
            String[] result = generateRandomSourceAndPattern();
            long startTime1 = System.currentTimeMillis();
            String s1 = getMostMatch2(result[0],result[1]);
            long endTime1 = System.currentTimeMillis();
            time1 += (endTime1-startTime1);
            long startTime2 = System.currentTimeMillis();
            String s2 = getMostMatch3(result[0],result[1]);
            long endTime2 = System.currentTimeMillis();
            time2 += (endTime2-startTime2);
            if(!s1.equals(s2)){
                right = false;
                System.out.println(result[0]);
                System.out.println(result[1]);
                System.out.println(s1);
                System.out.println(s2);
                break;
            }
        }
        if(right){
            System.out.println("Yeah, all right.");
            System.out.println("程序1平均运行时间："+(time1/10000)+"ms");
            System.out.println("程序2平均运行时间："+(time2/10000)+"ms");
        }
        else{
            System.out.println("Fucking fucked.");
        }
    }

    private static String[] generateRandomSourceAndPattern(){
        Random random = new Random();
        String[] result = new String[2];
        //源文本长度1000
        StringBuilder source = new StringBuilder(10000);
        //模式文本长度500
        StringBuilder pattern = new StringBuilder(5000);
        for(int i = 0; i < 5000; i++){
            source.append((char)(random.nextInt(26)+'a'));
            source.append((char)(random.nextInt(26)+'a'));
            pattern.append((char)(random.nextInt(26)+'a'));
        }
        result[0] = source.toString();
        result[1] = pattern.toString();
        //返回一个长度为2的字符串数组，第一个作为source，长度为1000，只包含随机的a-z
        //第二个作为pattern，长度为500，只包含随机的a-z
        return result;
    }

    public static String getMostMatch3(String source, String pattern){
        int maxLength = 0;
        int sourceIndex = 0;
        int patternIndex = 0;
        int patternLength = pattern.length();
        int sourceLength = source.length();
        while(sourceIndex < sourceLength){
            while(sourceIndex < sourceLength && patternIndex < patternLength && source.charAt(sourceIndex) == pattern.charAt(patternIndex)){
                sourceIndex++;
                patternIndex++;
            }
            /**
             * 假设样本为112231234 123
             * 如果patternIndex不为0，说明遇到了sourceIndex指向第二个1，patternIndex指向2的情况，则此时sourceIndex不需要变化
             * 而patternIndex需要归0
             * 如果patternIndex为0，则说明遇到了两个确实不相等的情况，将sourceIndex加一，而patternIndex无需变化
             */
            if(patternIndex != 0){
                maxLength = maxLength < patternIndex ? patternIndex : maxLength;
                patternIndex = 0;
            }
            else{
                sourceIndex++;
            }
        }
        return pattern.substring(0, maxLength);
    }

    public static String getMostMatch2(String source, String pattern) {
        String result ="";
        int i=0,j=0,len=0,tmpLen=0;
        int textLen = source.length();
        int patternLen = pattern.length();
        boolean isEnterLoop = false;
        while(i<textLen) {
            while(i<textLen&&j<patternLen&&source.charAt(i)==pattern.charAt(j)) {
                i++;j++;isEnterLoop =true;
            }
            if(j>len)
                len=j;
            j=0;
            if(!isEnterLoop)
                i++;
            isEnterLoop=false;
        }
        result = pattern.substring(0, len);
        return result;
    }

    public static String getMostMatch(String source, String pattern) {
        int beginIndex = 0;
        int maxLength = 0;
        int tempBeginIndex = 0;
        int currentLength = 0;
        int sourceLen = source.length();
        int patternLen = pattern.length();
        int patternIndex = 0;
        int sourceIndex = 0;
        while (sourceIndex < sourceLen && patternIndex < patternLen) {
            if (source.charAt(sourceIndex) == pattern.charAt(patternIndex)) {
                if (currentLength == 0) {
                    tempBeginIndex = sourceIndex;
                }
                sourceIndex++;
                patternIndex++;
                currentLength++;
            } else {
                if (currentLength == 0) {
                    sourceIndex++;
                    continue;
                }
                patternIndex = 0;
                if (maxLength < currentLength) {
                    maxLength = currentLength;
                    beginIndex = tempBeginIndex;
                }
                currentLength = 0;
            }
        }
        return (maxLength == 0 && currentLength == 0) ? "" : currentLength > maxLength ? pattern.substring(0, currentLength) : pattern.substring(0, maxLength);
    }
}

package dahua_ds;

import java.util.Arrays;
import java.util.Scanner;

//@LeetcodeFinished
//@Except getNextVal
public class KMPMatch {
    /**
     * 朴素匹配算法
     * 当两个字符相等时，源字符串和pattern字符串索引都加1
     * 如果不相等，二者的索引都回退
     * @param s
     * @param pattern
     * @return
     */
    private static int plainMatch(String s, String pattern) {
        int sLen = s.length(), pLen = pattern.length(), si = 0, pi = 0;
        while (si < sLen && pi < pLen) {
            if (s.charAt(si) == pattern.charAt(pi)) {
                ++si;
                ++pi;
            }
            else {
                pi = 0;
                si = si - pi + 1;
            }
        }
        if (pi == pLen) {
            return si - pLen;
        }
        return -1;
    }


    /**
     * 使用next数组可以在不相等时不用回溯si
     * 当pi=-1时，说明pi为0时不匹配，此时两个索引都加1
     * 这也是next第一个为-1的原因，如果为0，只能原地打转，同时设置为-1，++之后又变成了0
     * @param s
     * @param pattern
     * @return
     */
    private static int KMPMatch(String s, String pattern) {
        int sLen = s.length(), pLen = pattern.length(), si = 0, pi = 0;
        int[] next = getNext(pattern);
//        int[] next = getNext2(pattern);
//        int[] next = getNextVal(pattern);
        while (si < sLen && pi < pLen) {
            if (pi == -1 || s.charAt(si) == pattern.charAt(pi)) {
                ++si;
                ++pi;
            }
            else {
                pi = next[pi];
            }
        }
        if (pi == pLen) {
            return si - pLen;
        }
        return -1;
    }
    /**
     * 得到next数组
     * DABCDABDE [-1, 0, 0, 0, 0, 1, 2, 3, 1]
     * 对第三个字符B来说，next数组中对应值代表着DA中前缀和后缀相同的最大长度
     * 对于第一个字符，使用-1用来做特殊标记
     * 对于之后的项，应该这么计算：
     * 计算next[j]时，如果charAt(next[j-1])==charAt(j-1)，那么next[j]=next[j-1]+1
     * 否则，根据对称性原理令k=next[k]，一直往前找，能找到，则next[j]=k+1，否则为0
     * @param pattern
     * @return
     */
    private static int[] getNext(String pattern) {
        int pLen = pattern.length();
        int[] next = new int[pLen];
        next[0] = -1;
        int j = 1;
        while (j < pLen) {
            int k = next[j-1];
            while (k != -1 && pattern.charAt(k) != pattern.charAt(j-1)) {
                k = next[k];
            }
            next[j] = k + 1;
            ++j;
        }
        return next;
    }

    /**
     * 在一些网站上使用的方法，个人感觉没有getNext写的清晰
     * @param pattern
     * @return
     */
    private static int[] getNext2(String pattern) {
        int pLen = pattern.length();
        int[] next = new int[pLen];
        next[0] = -1;
        int j = 1;
        int k = next[0];
        while (j < pLen) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j-1)) {
                next[j] = k + 1;
                k = next[j];
                ++j;
            }
            else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * https://blog.csdn.net/v_JULY_v/article/details/7041827
     * 参考这里面，可以对next数组做出优化，但是我还么看懂
     * 这个答案是错误的，leetcode上没通过
     * @param pattern
     * @return
     */
    private static int[] getNextVal(String pattern) {
        int pLen = pattern.length();
        int[] next = new int[pLen];
        next[0] = -1;
        int j = 1;
        int k = next[0];
        while (j < pLen) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j-1)) {
                if (pattern.charAt(j) != pattern.charAt(k+1)) {
                    next[j] = k + 1;
                }
                else {
                    next[j] = next[k+1];
                }
                k = next[j];
                ++j;
            }
            else {
                k = next[k];
            }
        }
        return next;
    }
    /**
     * 得到最大长度表
     * DABCDABDE [0, 0, 0, 0, 1, 2, 3, 1, 0]
     * @param pattern
     * @return
     */
    private static int[] getMaxLenTable(String pattern) {
        int pLen = pattern.length();
        int[] result = new int[pLen];
        result[0] = 0;
        int j = 1;
        while (j < pLen) {
            int k = result[j-1];
            while (k != 0 && pattern.charAt(k) != pattern.charAt(j)) {
                k = result[k];
            }
            result[j] = pattern.charAt(k) == pattern.charAt(j) ? k+1 : 0;
            ++j;
        }
        return result;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
//            String pattern = scanner.nextLine();
//            System.out.println(plainMatch(s, pattern));
            System.out.println(Arrays.toString(getNext2(s)));
            System.out.println(Arrays.toString(getMaxLenTable(s)));

        }
    }
}

public class Solution {
    public int NumberOf1(int n) {
        int result = 0;
        while(n != 0){
            result += (n & 1);
            n >>>= 1;
            //也可以使用(n-1)&n，这样会将n的二进制位从最右边的1开始取反，然后再进行与运算，会将后面的二进制位全设置为0
            //如1100-1=1011 1011&1100=1000，这样最后与的结果肯定是0，且次数为二进制中1的数量
        }
        return result;
    }
}
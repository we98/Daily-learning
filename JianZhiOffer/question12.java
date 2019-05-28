public class Solution {
    public double Power(double base, int exponent) {
        if(base == 0){
            if(exponent == 0){
                return 1;
            }
            else if(exponent > 0){
                return 0;
            }
            else{
                throw new RuntimeException();
            }
        }
        //利用二进制位1011: 1000 0010 0001，在每次判断当前位都类乘base，当当前为为1时，类乘到结果上
        //复杂度logn
        int absExponent = exponent > 0 ? exponent : -exponent;
        double result = 1.0;
        while(absExponent != 0){
            if((absExponent & 1) == 1){
                result *= base;
            }
            base *= base;
            absExponent >>= 1;
        }
        return exponent > 0 ? result : 1/result;
  }
}
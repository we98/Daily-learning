public class Solution {
    public int Fibonacci(int n) {
        if(n < 0){
            return 0;
        }
        int first = 0;
        int second = 1;
        while(n-- != 0){
            second += first;
            first = second - first;
        }
        return first;
    }
}
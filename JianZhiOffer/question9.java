public class Solution {
    public int JumpFloorII(int target) {
        //fn = f(n-1) + ... + f(1) + f(0)
        //f(n-1) = f(n-2) + ... + f(1) + f(0);
        //f(n) = 2 * f(n-1)
        if(target <= 0){
            return -1;
        }
        return 1 << (target - 1);
    }
}
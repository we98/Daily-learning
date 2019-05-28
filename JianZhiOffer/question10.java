public class Solution {
    public int RectCover(int target) {
        //仍然是斐波那契数列
        if(target == 0){
            return 0;
        }
        int f = 1, g = 2;
        while(--target != 0){
            g += f;
            f = g - f;
        }
        return f;
    }
}
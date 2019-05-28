public class Solution {
    public int JumpFloor(int target) {
        int f = 1, g = 2;
        while(--target != 0){
            g += f;
            f = g - f;
        }
        return f;
        //return jump(target);
    }
//    private int jump(int remaining){
//        if(remaining == 0 || remaining == 1){
//            return remaining;
//        }
//        else if(remaining == 2){
//            return 2;
//        }
//        return jump(remaining - 1) + jump(remaining - 2);
//    }
}
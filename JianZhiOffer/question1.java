public class Solution {
    public boolean Find(int target, int [][] array) {
        if(array == null || array[0] == null){
            return false;
        }
        int currentX = array.length - 1;
        int currentY = 0;
        int maxY = array[0].length;
        while(currentX > -1 && currentY < maxY){
            int currentValue = array[currentX][currentY];
            if(target > currentValue){
                ++currentY;
            }
            else if(target < currentValue){
                --currentX;
            }
            else{
                return true;
            }
        }
        return false;
    }
}
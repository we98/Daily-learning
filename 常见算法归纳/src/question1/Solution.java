package question1;
/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * 思路：传统的方法从数组起始处开始查找，因为向下和向右都有大于自己的数字，所以方向不确定。
 * 因此，从数组的左下角作为起始点开始查找，比自身小，向上，比自身大，向右。
 * 因为数组的性质满足在某一点处其右下方一定大于自己，其左上方一定都小于自己。
 * 因此，通过这个性质，以左下角作为起始点，不断的向上和向右，无需向左和向下回溯，便可以找到目标。
 * 
 * @author CGWEI
 * 
 */
public class Solution {
	public static void main(String[] args) {
		
	}
	public boolean Find(int target, int [][] array) {
        int actualColumn = array[0].length - 1;
        int row = array.length - 1;
        int column = 0;
        while(row >= 0 && column <= actualColumn){
            if(target == array[row][column]){
                return true;
            }
            else if(target > array[row][column]){
                column++;
            }
            else{
                row--;
            }
        }
        return false;
    }
}
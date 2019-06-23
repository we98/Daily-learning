package question44;
/**
 * 
 * @author CGWEI
 *
 */
public class Solution{
	//四个函数形式基本一致
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 1, 2, 4, 4, 5, 7};
        //System.out.println(findExactly(array, 2));
        //System.out.println(findFirstLargerOrEqual(array, 10));
        //System.out.println(findFirstLarger(array, 10));
        //System.out.println(findLastSmallerOrEqual(array, -10));
        System.out.println(findLastSmaller(array, -10));
    }
    private static int findExactly(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        while(start <= end){
            int middle = (start + end) >> 1;
            if(array[middle] == target){
                return middle;
            }
            else if(array[middle] < target){
                start = middle + 1;
            }
            else{
                end = middle - 1;
            }
        }
        return -1;
    }
    /**
     * 不存在则返回array.length
     * @param array
     * @param target
     * @return
     */
    private static int findFirstLargerOrEqual(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        while(start <= end){
            int middle = (start + end) >> 1;
            if(array[middle] < target){
                start = middle + 1;
            }
            else{
                end = middle - 1;
            }
        }
        return start;
    }
    /**
     * 不存在则返回array.length
     * @param array
     * @param target
     * @return
     */
    private static int findFirstLarger(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        while(start <= end){
            int middle = (start + end) >> 1;
            if(array[middle] <= target){
                start = middle + 1;
            }
            else{
                end = middle - 1;
            }
        }
        return start;
    }
    /**
     * 不存在则返回-1
     * @param array
     * @param target
     * @return
     */
    private static int findLastSmallerOrEqual(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        while(start <= end){
            int middle = (start + end) >> 1;
            if(array[middle] <= target){
                start = middle + 1;
            }
            else{
                end = middle - 1;
            }
        }
        return end;
    }
    /**
     * 不存在则返回-1
     * @param array
     * @param target
     * @return
     */
    private static int findLastSmaller(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        while(start <= end){
            int middle = (start + end) >> 1;
            if(array[middle] < target){
                start = middle + 1;
            }
            else{
                end = middle - 1;
            }
        }
        return end;
    }
}

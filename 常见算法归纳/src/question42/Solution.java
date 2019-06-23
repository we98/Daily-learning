package question42;

import java.util.Arrays;

/**
 * 
 * @author CGWEI
 *
 */
public class Solution{
	public static void main(String[] args) {
		int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
	}
	
	public static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int middle = (start + end) >> 1;
            if(nums[middle] == target) {
                start = middle;
                while(start > -1 && nums[start] == target) {
                    start--;
                }
                end = middle;
                while(end < nums.length && nums[end] == target) {
                    end++;
                }
                return new int[] {start + 1, end - 1};
            }
            else if(nums[middle] > target) {
                end = middle - 1;
            }
            else {
                start = middle + 1;
            }
        }
        return new int[] {-1, -1};
    }
}

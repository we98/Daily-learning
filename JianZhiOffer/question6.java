import java.util.ArrayList;
public class Solution {
	//思路是：利用旋转数组局部有序使用二分。
	//先考虑没有相等的情况，则算法开始时，left对应的值是第一个递增段的最小值，right对应的值是第二个递增段的最大值，且left一定大于right的值
	//这样的话，每次取middle，如果大于left的值，则middle位于第一个段上，使left=middle，如果小于，则使right=middle
	//这样left与right一直逼近，但是却不会相等，是因为left肯定在第一个段上，right肯定在第二段上，当二者相差1时，right对应的值即为最小
	//再考虑可能存在想等的情况，如果left与right对应的值相等，则无法判断此时middle到底在哪个段上，只能通过暴力求解
    public int minNumberInRotateArray(int [] array) {
        if(array.length < 1){
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int middle = 0;
		//用于确认数组肯定是旋转的，事实上，题目已经告知是旋转了，所以直接设置为true也可以
        while(array[left] >= array[right]){
            if(right - left == 1){
                break;
            }
            middle = (left + right) >> 1;
			//当left与right相等时，无法判断middle在哪个段上
            if(array[middle] == array[left] && array[middle] == array[left]){
                return getMin(array, left, right);
            }
            else if(array[middle] >= array[left]){
                left = middle;
            }
            else if(array[middle] <= array[right]){
                right = middle;
            }
        }
        return array[right];
    }
    private int getMin(int[] array, int left, int right){
        int min = array[left];
        for(int i = left + 1; i <= right; ++i){
            if(array[i] < min){
                min = array[i];
                break;
            }
        }
        return min;
    }
}
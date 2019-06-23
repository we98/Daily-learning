package question7;

import java.util.Arrays;

import javax.print.attribute.standard.RequestingUserName;

/**
 * 快排、partition、荷兰国旗问题
 * 
 * 实质都是partition
 * 
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		testQuickSort();
		int[] nums = {2, 5, 7, 9, -1};
		System.out.println(new Solution().kthLargestElement(2, nums));
	}
	
	
	/**
	 * partition问题
	 * 
	 * 给定一个数组array，和一个数value，请把小于等于value的数放在数组的左边，大于等于value的数放在数组右边
	 * 也可以给定一个数组array，和一个下标index，请把小于等于array[index]的数放在数组的左边，大于等于array[index]的数放在数组右边
	 * 返回分界点
	 * 
	 * partition实质上，左边的less下标维护了一个小于等于value的左半部分，初始值为-1，代表这个区域里没有任何数字 
	 * @param array
	 * @param value
	 */
	private static int partition(int[] array, int index) {
		int partitionValue = array[index];
		int less = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] <= partitionValue) {
				swap(array, ++less, i);
			}
		}
		return less;
	}
	private static int partition(int[] array, int left, int right) {
		int partitionValue = array[right];
		int less = left - 1;
		
		for(int i = left; i <= right; i++) {
			if(array[i] <= partitionValue) {
				swap(array, ++less, i);
			}
		}
		return less;
	}
	
	
	/**
	 * 荷兰国旗问题
	 * 
	 * 给定一个数组array和一个数值value，将小于value的值放左边，等于放中间，大于放右边
	 * 
	 * 初始时less为-1，more为length，分别代表less和more的区域不存在
	 * @param array
	 * @param value
	 */
	private static void NetherlandsFlag(int[] array, int value) {
		int less = -1;
		int more = array.length;
		int current = 0;
		while(current < more) {
			if(array[current] == value) {
				current++;
			}
			else if(array[current] < value) {
				swap(array, ++less, current++);
			}
			else {
				swap(array, current, --more);
			}
		}
	}
	private static int[] partitionWithNetherlandsFlag(int[] array, int left, int right) {
		int partitionValue = array[right];
		int less = left - 1;
		int more = right + 1;
		int current = left;
		while(current < more) {
			if(array[current] == partitionValue) {
				current++;
			}
			else if(array[current] < partitionValue){
				swap(array, ++less, current++);
			}
			else {
				swap(array, --more, current);
			}
		}
		return new int[] {less+1, more-1};
	}
	/**
	 * 改进后的快速排序效率更高，通过荷兰国旗问题，将数组划分为三部分，实现三路快排
	 * 而经典的快速排序，只是保证数组被划分为 <=x x >x 这三部分，每次递归只搞定一个数，而通过荷兰国旗问题每次可以搞定一堆相等的数
	 * @param array
	 */
	public static void quickSort(int[] array) {
		if(array == null || array.length < 2) {
			return;
		}
		quickSort(array, 0, array.length-1);
	}
	public static void quickSort(int[] array, int left, int right) {
		if(right <= left) {
			return;
		}
		/**
		 * 随机交换，可以提高算法的平均效率，解决每次array[right]是极端值的问题
		 * 假如如果数组已经有序，这时如果不随机交换而强行使用最后一个变量，每个partition只会完成最后一个变量的排序，因此复杂度退化为N2
		 * 加上这一行之后快排被称为随机快排
		 */
		swap(array, left+(int)(Math.random())*(right-left+1), right);
		int[] temp = partitionWithNetherlandsFlag(array, left, right);
		quickSort(array, left, temp[0]-1);
		quickSort(array, temp[0]+1, right);
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * 得到数组中第k大元素
	 * @param n
	 * @param nums
	 * @return
	 */
	public int kthLargestElement(int n, int[] nums) {
		//计算第K大元素从小到大排列后所在的位置
    	int flag = nums.length - n;
    	quickSortK(flag, nums, 0, nums.length - 1);
		return nums[flag];
    }
    private void quickSortK(int flag, int[] nums, int left, int right) {
    	if(right <= left) {
    		return;
    	}
    	swap(nums, left+(int)(Math.random())*(right-left+1), right);
    	int[] temp = partitionWithNetherlandsFlag(nums, left, right);
    	//当flag位于temp0和temp1之间时，说明第K大元素已经找到了自己的位置
    	if(temp[0] <= flag && temp[1] >= flag) {
    		return;
    	}
    	else if(flag < temp[0]) {
    		quickSortK(flag, nums, left, temp[0] - 1);
    	}
    	else {
    		quickSortK(flag, nums, temp[1] + 1, right);
    	}
	}
    

	
	//以下代码为对数器代码，也就是测试代码。需要以下几个模块：随机数组发生器、一个绝对正确的方法、比对方法、测试方法
	/**
	 * 随机数组发生器，可以产生长度为[0,size]之间，值为[-value,value]之间的数组
	 * 注意测试的时候不要将size设置过大，可以设置很大的测试次数（万级别的次数）
	 * 因为通过测试次数可以穷尽各种情况，同时size不是很大也容易排查错误的来源
	 * @param size
	 * @param value
	 * @return
	 */
	private static int[] generateRandomArray(int size, int value) {
		int[] array = new int[(int)((size+1)*Math.random())];
		for(int i = 0; i < array.length; i++) {
			array[i] = (int)((value+1)*Math.random()) - (int)((value+1)*Math.random());
		}
		return array;
	}
	/**
	 * 绝对正确的方法，可以调用库函数，当然也可以自己写
	 * （自己写一个不符合题目要求的，比如时间复杂度，空间复杂度不符合要求，但是一定正确，并且能快速写出来的）
	 * @param array
	 */
	private static void absoluteRightMethod(int[] array) {
		Arrays.sort(array);
	}
	/**
	 * 用来与完全正确的方法配合的数组备份
	 * @param source
	 * @return
	 */
	private static int[] copyArray(int[] source) {
		if(source == null) {
			return null;
		}
		int[] array = new int[source.length];
		System.arraycopy(source, 0, array, 0, source.length);
		return array;
	}
	/**
	 * 用来判断自己写的方法的结果是否和一定正确的方法的结果是否相等
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	private static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 用于当结果错误时，打印出当前数组，便于分析
	 * @param array
	 */
	private static void printArray(int[] array) {
		Arrays.toString(array);
	}
	/**
	 * 正式的测试函数
	 */
	private static void testQuickSort() {
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 5;
		boolean succeed = true;
		for(int i = 0; i < testTime; i++) {
			int[] array1 = generateRandomArray(maxSize, maxValue);
			int[] array2 = copyArray(array1);
			int[] array3 = copyArray(array1);
			quickSort(array1);
			absoluteRightMethod(array2);
			if(!isEqual(array1, array2)) {
				succeed = false;
				System.out.println("The array cannot suit your method:");
				printArray(array3);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
	
}
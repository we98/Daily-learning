package question6;

import java.util.Arrays;

/**
 * 归并排序，以及由基于数组分治处理的数组小和问题、数组逆序对问题
 * 对数组使用递归来操作均可以使用归并排序的思想
 * 
 * @author CGWEI
 *
 */
public class Solution {
	public static void main(String[] args) {
		testMergeSort();
		testSmallSum();
		int[] array = {-1,8,1,10,7,2};
		printReversedPairs(array);
	}
	
	/**
	 * 归并排序算法
	 * @param array
	 */
	public static void mergeSort(int[] array) {
		if(array == null || array.length <= 1) {
			return;
		}
		mergeSort(array, 0, array.length-1);
	}
	private static void mergeSort(int[] array, int left, int right) {
		if(left == right) {
			return;
		}
		int middle = (left + right)/2;
		mergeSort(array, left, middle);
		mergeSort(array, middle+1, right);
		merge(array, left, middle, right);
	}
	private static void merge(int[] array, int left, int middle, int right) {
		int[] temp = new int[right - left + 1];
		int capaity = 0;
		int leftIndex = left;
		int middleIndex = middle + 1;
		while(leftIndex <= middle && middleIndex <= right) {
			temp[capaity++] = array[leftIndex] <= array[middleIndex] ? array[leftIndex++] : array[middleIndex++];
		}
		while(leftIndex <= middle) {
			temp[capaity++] = array[leftIndex++];
		}
		while(middleIndex <= right) {
			temp[capaity++] = array[middleIndex++];
		}
		for(int i = left; i <= right; i++) {
			array[i] = temp[i - left];
		}
	}
	
	//以下代码为对数器代码，也就是测试代码。需要以下几个模块：随机数组发生器、一个绝对正确的方法、比对方法、测试方法
	/**
	 * 随机数组发生器，可以产生长度为(0,size]之间，值为[-value,value]之间的数组
	 * 注意测试的时候不要将size设置过大，可以设置很大的测试次数（万级别的次数）
	 * 因为通过测试次数可以穷尽各种情况，同时size不是很大也容易排查错误的来源
	 * @param size
	 * @param value
	 * @return
	 */
	private static int[] generateRandomArray(int size, int value) {
		int[] array = new int[(int)((size+1)*Math.random()) + 1];
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
		System.out.println(Arrays.toString(array));
	}
	/**
	 * 正式的测试函数
	 */
	private static void testMergeSort() {
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 5;
		boolean succeed = true;
		for(int i = 0; i < testTime; i++) {
			int[] array1 = generateRandomArray(maxSize, maxValue);
			int[] array2 = copyArray(array1);
			int[] array3 = copyArray(array1);
			mergeSort(array1);
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
	
	
	/**
	 * 通过归并排序的小和问题
	 * @param array
	 * @return
	 */
	public static int smallSumOfArray(int[] array) {
		if(array == null) {
			throw new RuntimeException("array null");
		}
		if(array.length < 2) {
			return 0;
		}
		return smallSumOfArray(array, 0, array.length - 1);
	}
	private static int smallSumOfArray(int[] array, int left, int right) {
		if(left == right) {
			return 0;
		}
		int sum = 0;
		int middle = (left + right) / 2;
		sum += smallSumOfArray(array, left, middle);
		sum += smallSumOfArray(array, middle+1, right);
		sum += getMergeResult(array, left, middle, right);
		return sum;
	}
	private static int getMergeResult(int[] array, int left, int middle, int right) {
		int sum = 0;
		int[] temp = new int[right - left + 1];
		int capaity = 0;
		int leftIndex = left;
		int middleIndex = middle + 1;
		while(leftIndex <= middle && middleIndex <= right) {
			if(array[leftIndex] < array[middleIndex]) {
				temp[capaity++] = array[leftIndex];
				sum += array[leftIndex] * (right - middleIndex + 1);
				leftIndex++;
			}
			else{
				temp[capaity++] = array[middleIndex++];
			}
			//sum += array[leftIndex] < array[middleIndex]? 
			//temp[capaity++] = array[leftIndex] < array[middleIndex] ? array[leftIndex++] : array[middleIndex++];
		}
		while(leftIndex <= middle) {
			temp[capaity++] = array[leftIndex++];
		}
		while(middleIndex <= right) {
			temp[capaity++] = array[middleIndex++];
		}
		for(int i = left; i <= right; i++) {
			array[i] = temp[i - left];
		}
		return sum;
	}
	private static int absoluteRightMethodOfSmallSum(int[] array) {
		if(array == null) {
			throw new RuntimeException("array null.");
		}
		if(array.length < 2) {
			return 0;
		}
		int sum = 0;
		for(int i = 0; i < array.length-1; i++) {
			int count = 0;
			for(int j = i+1; j < array.length; j++) {
				if(array[j] > array[i]) {
					count++;
				}
			}
			sum += array[i] * count;
		}
		return sum;
	}
	private static void testSmallSum() {
		int testTime = 10000;
		int maxSize = 5;
		int maxValue = 5;
		boolean succeed = true;
		for(int i = 0; i < testTime; i++) {
			int[] array1 = generateRandomArray(maxSize, maxValue);
			int[] array2 = copyArray(array1);
			int[] array3 = copyArray(array1);
			if(smallSumOfArray(array1) != absoluteRightMethodOfSmallSum(array2)) {
				succeed = false;
				System.out.println("The small sum of array is not correct:");
				printArray(array3);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
	
	
	/**
	 * 数组的逆序对问题，跟上面的小和问题一模一样
	 * @param array
	 */
	private static void printReversedPairs(int[] array) {
		if(array == null || array.length < 2) {
			return;
		}
		printReversedPairs(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	private static void printReversedPairs(int[] array, int left, int right) {
		if(left == right) {
			return;
		}
		int middle = (left + right) / 2;
		printReversedPairs(array, left, middle);
		printReversedPairs(array, middle+1, right);
		printMergedResult(array, left, middle, right);
	}
	private static void printMergedResult(int[] array, int left, int middle, int right) {
		int[] temp = new int[right-left+1];
		int capacity = 0;
		int leftIndex = left;
		int middleIndex = middle + 1;
		while(leftIndex <= middle && middleIndex <= right) {
			if(array[leftIndex] > array[middleIndex]) {
				for(int i = middleIndex; i <= right; i++) {
					System.out.println("{" + array[leftIndex] + "," + array[i] + "}");
				}
				temp[capacity++] = array[leftIndex++];
			}
			else {
				temp[capacity++] = array[middleIndex++];
			}
			//System.out.println(array[leftIndex] < array[middleIndex] ? "" : "{"+array[leftIndex]+","+array[middleIndex]+"}");
			//temp[capacity++] = array[leftIndex] > array[middleIndex] ? array[leftIndex++] : array[middleIndex++];
		}
		while(leftIndex <= middle) {
			temp[capacity++] = array[leftIndex++];
		}
		while(middleIndex <= right) {
			temp[capacity++] = array[middleIndex++];
		}
		for(int i = left; i <= right; i++) {
			array[i] = temp[i - left];
		}
	}
	
	
	
	
}
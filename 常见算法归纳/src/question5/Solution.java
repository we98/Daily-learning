package question5;

import java.util.Arrays;

/**
 * 各种基本排序算法
 * 
 * 冒泡排序、选择排序、插入排序
 * 
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		testAllSort();
	}
	/**
	 * 冒泡排序，实质上是两两比较，每次讲最大数移至最后一位
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		if(array == null || array.length == 1) {
			return;
		}
		for(int i = 1; i < array.length; i++) {
			//加入end标记可以很好的优化冒泡排序
			boolean end = true;
			for(int j = 0; j < array.length - i; j++) {
				if(array[j] > array[j+1]) {
					end = false;
					swap(array, j, j+1);
				}
			}
			if(end) {
				break;
			}
		}
	}
	/**
	 * 选择排序，每次将最小的数记录下来，同时将这个数与前面的数交换
	 * @param array
	 */
	public static void selectSort(int[] array) {
		if(array == null || array.length == 1) {
			return;
		}
		for(int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			for(int j = i+1; j < array.length; j++) {
				if(array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			if(minIndex != i) {
				swap(array, i, minIndex);
			}
		}
	}
	/**
	 * 插入排序，相当于将某个元素插入前面的某几个已经排好的数组中，最初将第一个元素作为排好的数组
	 * 插入排序的最好情况复杂度要优于冒泡排序和选择排序，（未优化的）冒泡和选择如果在数组已经排好序的情况下是O(N2)，而插入是O(N)
	 * @param array
	 */
	public static void insertSort(int[] array) {
		if(array == null || array.length == 1) {
			return;
		}
		for(int i = 1; i < array.length; i++) {
			for(int j = i-1; j >= 0; j--) {
				if(array[j] > array[j+1]) {
					swap(array, j, j+1);
				}
				else {
					break;
				}
			}
		}
	}
	private static void swap(int[] array, int i, int j) {
//		array[i] = array[i] ^ array[j];
//		array[j] = array[i] ^ array[j];
//		array[j] = array[i] ^ array[j];
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
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
	private static void testAllSort() {
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 5;
		boolean succeed = true;
		for(int i = 0; i < testTime; i++) {
			int[] array1 = generateRandomArray(maxSize, maxValue);
			int[] array2 = copyArray(array1);
			int[] array3 = copyArray(array1);
			bubbleSort(array1);
//			selectSort(array1);
//			insertSort(array1);
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
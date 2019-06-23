package question8;

import java.util.Arrays;

/**
 * 堆是一个很重要的数据结构，可以认为是一棵完全二叉树，完全二叉树是满二叉树的叶子节点从左到右依次补齐
 * 堆用数组实现，左孩子是2*i+1，右孩子是2*i+2，(i-1)/2就是其父节点
 * 堆分为大根堆和小跟堆，大根堆是指任何子树的根节点的值大于任何它的子节点，作用类似于优先权队列
 * 
 * 堆排序的过程为，假设需要将数组从小到大排列，则首先需要建立一个大根堆（通过遍历数组以及向上调整的过程）
 * 建立大根堆并不代表数组已经完全有序，只能代表根节点也就是数组中的第一个元素已经确定为所有数的最大值
 * 此时，将根节点与数组中的最后一个元素交换位置，数组的最大值便成功被换到了数组的最后一位
 * 然后，被换到数组第一位的需要调整，因为被换了之后除了最后一个元素的数组已经可能不满足堆结构，所以便使用向下调整的方法将前面的元素重新调整为大根堆
 * 重复这个过程即可
 * 
 * @author CGWEI
 *
 */
public class Solution {
	public static void main(String[] args) {
		int[] array = {-1,8,1,10,7,2};
		heapSort(array);
		System.out.println(Arrays.toString(array));
		testHeapSort();
	}
	
	
	public static void heapSort(int[] array) {
		if(array == null || array.length < 2) {
			return;
		}
		buildHeap(array);
		int heapSize = array.length;
		while(heapSize > 1) {
			swap(array, 0, heapSize-1);
			adjustDown(array, --heapSize, 0);
		}
	}
	/**
	 * 建堆过程，其实是遍历数组，以最小堆为例，就是将小的元素不断向上也就是向根节点调节
	 * 建堆的过程空间复杂度计算：log1+log2+...+log(n-1) = O(N)
	 * @param array
	 */
	private static void buildHeap(int[] array) {
		for(int i = 1; i < array.length; i++) {
			adjustUp(array, i);
		}
	}
	//这个过程就是将新的节点加入到已经形成的堆中的过程，同时往上调整的过程
	private static void adjustUp(int[] array, int index) {
		//因为(0-1)/2 == 0，所以当遍历到0时，会自动退出
		while(array[index] > array[(index-1)/2]) {
			swap(array, index, (index-1)/2);
			index = (index-1)/2;
		}
	}
	//用于当数组中index下标的数发生改变时，重新将数组调整为大根堆（在堆排序中index都是0），且调整范围在heapSize内
	private static void adjustDown(int[] array, int heapSize, int index) {
		int child = 2 * index + 1;
		while(child < heapSize) {
			child = (child+1) < heapSize ? (array[child] > array[child+1] ? child : child+1) : child;
			if(array[index] >= array[child]) {
				break;
			}
			swap(array, index, child);
			index = child;
			child = 2 * index + 1;
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
	private static void testHeapSort() {
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 5;
		boolean succeed = true;
		for(int i = 0; i < testTime; i++) {
			int[] array1 = generateRandomArray(maxSize, maxValue);
			int[] array2 = copyArray(array1);
			int[] array3 = copyArray(array1);
			heapSort(array1);
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
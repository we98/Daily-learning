package question6;

import java.util.Arrays;

/**
 * �鲢�����Լ��ɻ���������δ��������С�����⡢�������������
 * ������ʹ�õݹ�������������ʹ�ù鲢�����˼��
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
	 * �鲢�����㷨
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
	
	//���´���Ϊ���������룬Ҳ���ǲ��Դ��롣��Ҫ���¼���ģ�飺������鷢������һ��������ȷ�ķ������ȶԷ��������Է���
	/**
	 * ������鷢���������Բ�������Ϊ(0,size]֮�䣬ֵΪ[-value,value]֮�������
	 * ע����Ե�ʱ��Ҫ��size���ù��󣬿������úܴ�Ĳ��Դ������򼶱�Ĵ�����
	 * ��Ϊͨ�����Դ�����������������ͬʱsize���Ǻܴ�Ҳ�����Ų�������Դ
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
	 * ������ȷ�ķ��������Ե��ÿ⺯������ȻҲ�����Լ�д
	 * ���Լ�дһ����������ĿҪ��ģ�����ʱ�临�Ӷȣ��ռ临�ӶȲ�����Ҫ�󣬵���һ����ȷ�������ܿ���д�����ģ�
	 * @param array
	 */
	private static void absoluteRightMethod(int[] array) {
		Arrays.sort(array);
	}
	/**
	 * ��������ȫ��ȷ�ķ�����ϵ����鱸��
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
	 * �����ж��Լ�д�ķ����Ľ���Ƿ��һ����ȷ�ķ����Ľ���Ƿ����
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
	 * ���ڵ��������ʱ����ӡ����ǰ���飬���ڷ���
	 * @param array
	 */
	private static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
	/**
	 * ��ʽ�Ĳ��Ժ���
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
	 * ͨ���鲢�����С������
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
	 * �������������⣬�������С������һģһ��
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
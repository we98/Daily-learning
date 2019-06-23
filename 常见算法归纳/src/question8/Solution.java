package question8;

import java.util.Arrays;

/**
 * ����һ������Ҫ�����ݽṹ��������Ϊ��һ����ȫ����������ȫ������������������Ҷ�ӽڵ���������β���
 * ��������ʵ�֣�������2*i+1���Һ�����2*i+2��(i-1)/2�����丸�ڵ�
 * �ѷ�Ϊ����Ѻ�С���ѣ��������ָ�κ������ĸ��ڵ��ֵ�����κ������ӽڵ㣬��������������Ȩ����
 * 
 * ������Ĺ���Ϊ��������Ҫ�������С�������У���������Ҫ����һ������ѣ�ͨ�����������Լ����ϵ����Ĺ��̣�
 * ��������Ѳ������������Ѿ���ȫ����ֻ�ܴ�����ڵ�Ҳ���������еĵ�һ��Ԫ���Ѿ�ȷ��Ϊ�����������ֵ
 * ��ʱ�������ڵ��������е����һ��Ԫ�ؽ���λ�ã���������ֵ��ɹ�����������������һλ
 * Ȼ�󣬱����������һλ����Ҫ��������Ϊ������֮��������һ��Ԫ�ص������Ѿ����ܲ�����ѽṹ�����Ա�ʹ�����µ����ķ�����ǰ���Ԫ�����µ���Ϊ�����
 * �ظ�������̼���
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
	 * ���ѹ��̣���ʵ�Ǳ������飬����С��Ϊ�������ǽ�С��Ԫ�ز�������Ҳ��������ڵ����
	 * ���ѵĹ��̿ռ临�Ӷȼ��㣺log1+log2+...+log(n-1) = O(N)
	 * @param array
	 */
	private static void buildHeap(int[] array) {
		for(int i = 1; i < array.length; i++) {
			adjustUp(array, i);
		}
	}
	//������̾��ǽ��µĽڵ���뵽�Ѿ��γɵĶ��еĹ��̣�ͬʱ���ϵ����Ĺ���
	private static void adjustUp(int[] array, int index) {
		//��Ϊ(0-1)/2 == 0�����Ե�������0ʱ�����Զ��˳�
		while(array[index] > array[(index-1)/2]) {
			swap(array, index, (index-1)/2);
			index = (index-1)/2;
		}
	}
	//���ڵ�������index�±���������ı�ʱ�����½��������Ϊ����ѣ��ڶ�������index����0�����ҵ�����Χ��heapSize��
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
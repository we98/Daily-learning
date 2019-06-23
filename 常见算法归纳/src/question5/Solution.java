package question5;

import java.util.Arrays;

/**
 * ���ֻ��������㷨
 * 
 * ð������ѡ�����򡢲�������
 * 
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		testAllSort();
	}
	/**
	 * ð������ʵ�����������Ƚϣ�ÿ�ν�������������һλ
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		if(array == null || array.length == 1) {
			return;
		}
		for(int i = 1; i < array.length; i++) {
			//����end��ǿ��Ժܺõ��Ż�ð������
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
	 * ѡ������ÿ�ν���С������¼������ͬʱ���������ǰ���������
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
	 * ���������൱�ڽ�ĳ��Ԫ�ز���ǰ���ĳ�����Ѿ��źõ������У��������һ��Ԫ����Ϊ�źõ�����
	 * ������������������Ӷ�Ҫ����ð�������ѡ�����򣬣�δ�Ż��ģ�ð�ݺ�ѡ������������Ѿ��ź�����������O(N2)����������O(N)
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
	
	
	
	
	//���´���Ϊ���������룬Ҳ���ǲ��Դ��롣��Ҫ���¼���ģ�飺������鷢������һ��������ȷ�ķ������ȶԷ��������Է���
	/**
	 * ������鷢���������Բ�������Ϊ[0,size]֮�䣬ֵΪ[-value,value]֮�������
	 * ע����Ե�ʱ��Ҫ��size���ù��󣬿������úܴ�Ĳ��Դ������򼶱�Ĵ�����
	 * ��Ϊͨ�����Դ�����������������ͬʱsize���Ǻܴ�Ҳ�����Ų�������Դ
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
		Arrays.toString(array);
	}
	/**
	 * ��ʽ�Ĳ��Ժ���
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
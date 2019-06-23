package question7;

import java.util.Arrays;

import javax.print.attribute.standard.RequestingUserName;

/**
 * ���š�partition��������������
 * 
 * ʵ�ʶ���partition
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
	 * partition����
	 * 
	 * ����һ������array����һ����value�����С�ڵ���value���������������ߣ����ڵ���value�������������ұ�
	 * Ҳ���Ը���һ������array����һ���±�index�����С�ڵ���array[index]���������������ߣ����ڵ���array[index]�������������ұ�
	 * ���طֽ��
	 * 
	 * partitionʵ���ϣ���ߵ�less�±�ά����һ��С�ڵ���value����벿�֣���ʼֵΪ-1���������������û���κ����� 
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
	 * ������������
	 * 
	 * ����һ������array��һ����ֵvalue����С��value��ֵ����ߣ����ڷ��м䣬���ڷ��ұ�
	 * 
	 * ��ʼʱlessΪ-1��moreΪlength���ֱ����less��more�����򲻴���
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
	 * �Ľ���Ŀ�������Ч�ʸ��ߣ�ͨ�������������⣬�����黮��Ϊ�����֣�ʵ����·����
	 * ������Ŀ�������ֻ�Ǳ�֤���鱻����Ϊ <=x x >x �������֣�ÿ�εݹ�ֻ�㶨һ��������ͨ��������������ÿ�ο��Ը㶨һ����ȵ���
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
		 * �����������������㷨��ƽ��Ч�ʣ����ÿ��array[right]�Ǽ���ֵ������
		 * ������������Ѿ�������ʱ��������������ǿ��ʹ�����һ��������ÿ��partitionֻ��������һ��������������˸��Ӷ��˻�ΪN2
		 * ������һ��֮����ű���Ϊ�������
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
	 * �õ������е�k��Ԫ��
	 * @param n
	 * @param nums
	 * @return
	 */
	public int kthLargestElement(int n, int[] nums) {
		//�����K��Ԫ�ش�С�������к����ڵ�λ��
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
    	//��flagλ��temp0��temp1֮��ʱ��˵����K��Ԫ���Ѿ��ҵ����Լ���λ��
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
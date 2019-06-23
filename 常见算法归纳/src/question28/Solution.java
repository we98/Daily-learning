package question28;


import java.util.PriorityQueue;

/**
 * �������飬���������Сֵ��
 * ˼·��
 * �������Բ�������˼ά�����ǽ�ÿһС�ν����ϲ�Ϊ�ܵĴ�����Ĺ����в����ķ���
 * �����Ƶ����������ʹ�ù�����������Ϊ����
 * @author CGWEI
 *
 */
public class Solution {
	public static int getMinCost(int[] array) {
		if(array == null || array.length < 1) {
			return 0;
		}
		int totalCost = 0;
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(array.length);
		for(int i = 0; i < array.length; i++) {
			priorityQueue.offer(array[i]);
		}
		while(priorityQueue.size() > 1) {
			int temp = priorityQueue.poll() + priorityQueue.poll();
			priorityQueue.offer(temp);
			totalCost += temp;
		}
		return totalCost;
	}
	
	public static void main(String[] args) {
		
		int[] array = {10, 20, 30, 40};
		System.out.println(getMinCost(array));
		
	}
	
}
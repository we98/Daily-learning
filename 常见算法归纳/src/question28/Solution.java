package question28;


import java.util.PriorityQueue;

/**
 * 给定数组，求产生的最小值。
 * 思路：
 * 这道题可以采用逆向思维，就是将每一小段金条合并为总的大金条的过程中产生的费用
 * 可以推导出，这道题使用哈夫曼建树即为所求
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
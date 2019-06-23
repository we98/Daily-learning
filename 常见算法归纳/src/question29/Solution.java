package question29;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定costs数组和profits数组，求在本金为w，最多投资项目为k的情况下，收益最大值
 * 思路：
 * 这道题的贪心策略是，在本金为w的情况下，选出投资小于w的所有项目，并按照从大到小的顺序投资它们
 * 相当于维护了两个队列，一个是按照本金从小到大的优先权队列，另一个是按照收益从大到小的优先权队列（这个队列可以被看作是所有已经被解锁的项目）
 * 因此这道题的解法就是按照这两个优先权队列来动态的决定投资的项目
 * @author CGWEI
 *
 */
public class Solution {
	
	private static class Project{
		int cost;
		int profit;
		public Project(int c, int p) {
			cost = c;
			profit = p;
		}
	}
	
	private static class CostComparator implements Comparator<Project>{
		//cost建立最小堆
		@Override
		public int compare(Project o1, Project o2) {
			return o1.cost - o2.cost;
		}
	}
	private static class ProfitComparator implements Comparator<Project>{
		//profit建立最大堆
		@Override
		public int compare(Project o1, Project o2) {
			return o2.profit - o1.profit;
		}
	}
	
	
	public static int getMaxProfit(int w, int k, int[] costs, int[] profits) {
		int totalMoney = w;
		PriorityQueue<Project> costPriorityQueue = new PriorityQueue<>(new CostComparator());
		PriorityQueue<Project> profitPriorityQueue = new PriorityQueue<>(new ProfitComparator());
		for(int i = 0; i < costs.length; i++) {
			Project project = new Project(costs[i], profits[i]);
			costPriorityQueue.offer(project);
		}
		for(int i = 0; i < k; i++) {
			while(!costPriorityQueue.isEmpty() && costPriorityQueue.peek().cost <= totalMoney) {
				profitPriorityQueue.offer(costPriorityQueue.poll());
			}
			if(profitPriorityQueue.isEmpty()) {
				break;
			}
			totalMoney += profitPriorityQueue.poll().profit;
		}
		return totalMoney;
	}
	
	public static void main(String[] args) {
		
	}
	
}
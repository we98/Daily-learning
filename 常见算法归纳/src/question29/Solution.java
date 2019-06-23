package question29;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ����costs�����profits���飬���ڱ���Ϊw�����Ͷ����ĿΪk������£��������ֵ
 * ˼·��
 * ������̰�Ĳ����ǣ��ڱ���Ϊw������£�ѡ��Ͷ��С��w��������Ŀ�������մӴ�С��˳��Ͷ������
 * �൱��ά�����������У�һ���ǰ��ձ����С���������Ȩ���У���һ���ǰ�������Ӵ�С������Ȩ���У�������п��Ա������������Ѿ�����������Ŀ��
 * ��������Ľⷨ���ǰ�������������Ȩ��������̬�ľ���Ͷ�ʵ���Ŀ
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
		//cost������С��
		@Override
		public int compare(Project o1, Project o2) {
			return o1.cost - o2.cost;
		}
	}
	private static class ProfitComparator implements Comparator<Project>{
		//profit��������
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
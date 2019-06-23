package question33;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	public static int getMostProfit(int[] weights, int[] profits, int capacity) {
		if(weights == null || profits == null || weights.length < 1 || weights.length != profits.length || capacity < 1) {
			return 0;
		}
		return getMostProfit(weights, profits, capacity, 0, 0, 0);
	}
	private static int getMostProfit(int[] weights, int[] profits, int capacity, int index, int currentWeight, int currentProfit) {
		if(currentWeight > capacity) {
			return 0;
		}
		if(index == weights.length) {
			return currentProfit;
			//return currentWeight > capacity ? -1 : currentProfit;
		}
		int p1 = getMostProfit(weights, profits, capacity, index+1, currentWeight+weights[index], currentProfit+profits[index]);
		int p2 = getMostProfit(weights, profits, capacity, index+1, currentWeight, currentProfit);
		return Math.max(p1, p2);
	}
	
	public static void main(String[] args) {
		int[] profits = {200, 240, 140, 150};
		int[] weights = {1, 3, 2, 5};
		int capacity = 6;
		System.out.println(getMostProfit(weights, profits, capacity));
	}
	
}
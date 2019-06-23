package question38;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	private static class Edge{
		public int from;
		public int to;
		public int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	private static class EdgeComparator implements Comparator<Edge>{
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
	}
	
	
	
	/**
	 * 最小生成树K算法
	 * 
	 * 题目描述： 
	 * 现在有孤岛n个，孤岛从1开始标序一直到n，有道路m条（道路是双向的，如果有多条道路连通岛屿i，j则选择最短的那条）
	 * 请你求出能够让所有孤岛都连通的最小道路总长度。
	 * 
	 * 输入： 
	 * 数据有多组输入。 
	 * 每组第一行输入n(1<=n<=1000),m(0<=m<=10000)。
	 * 
	 * 输出： 
	 * 对每组输入输出一行，如果能连通，输出能连通所有岛屿的最小道路长度，否则请输出字符串”no”。
	 * 
	 * 3 5 
	 * 1 2 2 
	 * 1 2 1 
	 * 2 3 5 
	 * 1 3 3 
	 * 3 1 2 
	 * 输出：3
	 * 
	 * 4 2 
	 * 1 2 3
	 * 2 3 7
	 * 输出：no
	 * 
	 * 思路：Kruskal算法+并查集
	 * 
	 */
	public static void kruskal() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[] parent = new int[n + 1];
		for(int i = 1; i < n+1; i++) {
			parent[i] = i;
		}
		//使用数组进行排序也行
		PriorityQueue<Edge> edges = new PriorityQueue<>(m, new EdgeComparator());
		while(m-- > 0) {
			edges.add(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
		}
		int result = 0;
		int edgeNumInTree = 0;
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			int root1 = find(parent, edge.from);
			int root2 = find(parent, edge.to);
			//如果两个点不在同一个子树中，则加入，若都在，就不用加了，否则会形成环路
			if(root1 != root2) {
				//union操作
				parent[root1] = root2;
				result += edge.weight;
				edgeNumInTree++;
			}
		}
		System.out.println(edgeNumInTree == n-1 ? result : "no");
	}
	private static int find(int[] parent, int place) {
		int copyPlace = place;
		while(parent[place] != place) {
			place = parent[place];
		}
		while(parent[copyPlace] != copyPlace) {
			int temp = parent[copyPlace];
			parent[copyPlace] = place;
			copyPlace = temp;
		}
		return place;
	}
	

	public static void main(String[] args) {	
		while(true)
		kruskal();
		
	}
}
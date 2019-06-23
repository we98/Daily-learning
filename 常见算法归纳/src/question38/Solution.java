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
	 * ��С������K�㷨
	 * 
	 * ��Ŀ������ 
	 * �����йµ�n�����µ���1��ʼ����һֱ��n���е�·m������·��˫��ģ�����ж�����·��ͨ����i��j��ѡ����̵�������
	 * ��������ܹ������йµ�����ͨ����С��·�ܳ��ȡ�
	 * 
	 * ���룺 
	 * �����ж������롣 
	 * ÿ���һ������n(1<=n<=1000),m(0<=m<=10000)��
	 * 
	 * ����� 
	 * ��ÿ���������һ�У��������ͨ���������ͨ���е������С��·���ȣ�����������ַ�����no����
	 * 
	 * 3 5 
	 * 1 2 2 
	 * 1 2 1 
	 * 2 3 5 
	 * 1 3 3 
	 * 3 1 2 
	 * �����3
	 * 
	 * 4 2 
	 * 1 2 3
	 * 2 3 7
	 * �����no
	 * 
	 * ˼·��Kruskal�㷨+���鼯
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
		//ʹ�������������Ҳ��
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
			//��������㲻��ͬһ�������У�����룬�����ڣ��Ͳ��ü��ˣ�������γɻ�·
			if(root1 != root2) {
				//union����
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
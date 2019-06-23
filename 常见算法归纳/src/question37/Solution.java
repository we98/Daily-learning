package question37;

import java.util.Scanner;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	/**
	 * ����������������֪��n���˺�m�Ժ��ѹ�ϵ����������r���������������ֱ�ӻ��ӵĺ��ѣ����ѵĺ��ѵĺ���...��
	 * ����Ϊ��������ͬһ������Ȧ����д���������n������һ���ж��ٸ�����Ȧ��
	 * 
	 * ��������������������ÿ�����������ĵ�һ�а������������� n��m��1=<n��m<=100000��
	 * ��������m�У�ÿ�зֱ����������˵ı��f��t��1=<f��t<=n������ʾf��t�Ǻ��ѡ� ��nΪ0ʱ�������������������������
	 * 
	 * ��Ӧÿ�������������������n������һ���ж��ٸ�����Ȧ��
	 * 
	 * 5 3 
	 * 1 2 
	 * 2 3 
	 * 4 5 
	 * �����2
	 * 
	 * 3 3 
	 * 1 2 
	 * 1 3 
	 * 2 3 
	 * �����1
	 * 
	 * 0
	 * �����
	 * 
	 * ��Դ��С��2013��У԰��Ƹ������
	 * 
	 * ˼·�������Ŀ�ǵ��͵�ʹ�ò��鼯�����⡣���Ƚ�ÿ���˶���ʼ��Ϊһ������������Ȧ�������������й�ϵ����ʱ��ֱ�ӽ������˺ϲ�Ϊͬһ����Ȧ
	 * �ϲ�����union��
	 * ���Ȳ����������Ƿ�����ͬһ����Ȧ����find������find������ԭ�����������˵Ĵ���ڵ㣬�������ڵ���ȣ�������ͬһ����Ȧ
	 * �������������ͬһ����Ȧ�����˳�union����������ڣ�������һ������Ȧ��ͷ�ڵ�Ĵ���ڵ����Լ���Ϊ��һ������Ȧ�Ĵ���ڵ�
	 * 
	 * ע�⣬find�����ڲ��ҵĹ����У�ͬʱ���Ż�������;�Ľڵ㶼ֱ��ָ�����ڵ�
	 * 
	 */
	public static void getFriendCircles() {
		Scanner scanner = new Scanner(System.in);
		int n = 0;
		if((n = scanner.nextInt()) == 0) {
			scanner.close();
			return;
		}
		//�����˵ı�ŷ�Χ��  1<= t, f <=n����������n+1���ռ䣬parent[n]��ʾ���Ϊn���˵ĸ��ڵ㣬parent[0]���ò���
		int[] parent = new int[n + 1];
		//��ô��λ�������Գ�һ��
		for(int i = 1; i < n+1; i++) {
			parent[i] = i;
		}
		int m = scanner.nextInt();
		while(m-- > 0){
			int f = scanner.nextInt();
			int t = scanner.nextInt();
			//�����������Ѻϲ�Ϊͬһ������Ȧ
			union(parent, f, t);
		}
		int friendCircles = 0;
		//�����Լ��Ĵ����Ǹ��ڵ㣬�ж��ٸ��ڵ���ж�������Ȧ
		for(int i = 1; i < n+1; i++) {
			if(parent[i] == i) {
				friendCircles++;
			}
		}
		System.out.println(friendCircles);
	}
	private static void union(int[] parent, int f, int t) {
		int fp = find(parent, f);
		int tp = find(parent, t);
		if(fp == tp) {
			return;
		}
		//parent[tp] = fp;Ҳ�У�˭��˭��һ��
		parent[fp] = tp;
	}
	//����x�Ĵ���ڵ�
	private static int find(int[] parent, int x) {
		//x�ı��ݣ������޸���;��ÿ���ڵ�ĸ��ڵ㣬�൱��ÿ�β��Ҷ����ٲ��Ż���·��
		int copyX = x;
		while(parent[x] != x) {
			x = parent[x];
		}
		//���·���������·�������еĽڵ㶼ֱ��ָ�����ڵ�
		while(parent[copyX] != copyX) {
			int temp = parent[copyX];
			parent[copyX] = x;
			copyX = temp;
		}
		return x;
	}
	
	public static void main(String[] args) {
		while(true) {
			getFriendCircles();
		}
	}
}
package question37;

import java.util.Scanner;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	/**
	 * 问题描述：假如已知有n个人和m对好友关系（存于数字r）。如果两个人是直接或间接的好友（好友的好友的好友...）
	 * 则认为他们属于同一个朋友圈，请写程序求出这n个人里一共有多少个朋友圈。
	 * 
	 * 输入包含多个测试用例，每个测试用例的第一行包含两个正整数 n、m，1=<n，m<=100000。
	 * 接下来有m行，每行分别输入两个人的编号f，t（1=<f，t<=n），表示f和t是好友。 当n为0时，输入结束，该用例不被处理。
	 * 
	 * 对应每个测试用例，输出在这n个人里一共有多少个朋友圈。
	 * 
	 * 5 3 
	 * 1 2 
	 * 2 3 
	 * 4 5 
	 * 输出：2
	 * 
	 * 3 3 
	 * 1 2 
	 * 1 3 
	 * 2 3 
	 * 输出：1
	 * 
	 * 0
	 * 无输出
	 * 
	 * 来源：小米2013年校园招聘笔试题
	 * 
	 * 思路：这个题目是典型的使用并查集的问题。首先将每个人都初始化为一个独立的朋友圈，当输入两个有关系的人时，直接将两个人合并为同一朋友圈
	 * 合并函数union：
	 * 首先查找两个人是否属于同一朋友圈，即find函数，find函数的原理是找两个人的代表节点，如果代表节点相等，则属于同一朋友圈
	 * 如果两个人属于同一朋友圈，则退出union，如果不属于，则将其中一个朋友圈的头节点的代表节点由自己改为另一个朋友圈的代表节点
	 * 
	 * 注意，find函数在查找的过程中，同时做优化，将沿途的节点都直接指向代表节点
	 * 
	 */
	public static void getFriendCircles() {
		Scanner scanner = new Scanner(System.in);
		int n = 0;
		if((n = scanner.nextInt()) == 0) {
			scanner.close();
			return;
		}
		//所有人的编号范围是  1<= t, f <=n，所以申请n+1个空间，parent[n]表示标号为n的人的父节点，parent[0]放置不用
		int[] parent = new int[n + 1];
		//这么多位朋友先自成一家
		for(int i = 1; i < n+1; i++) {
			parent[i] = i;
		}
		int m = scanner.nextInt();
		while(m-- > 0){
			int f = scanner.nextInt();
			int t = scanner.nextInt();
			//将这两个朋友合并为同一个朋友圈
			union(parent, f, t);
		}
		int friendCircles = 0;
		//等于自己的代表是根节点，有多少根节点就有多少朋友圈
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
		//parent[tp] = fp;也行，谁挂谁都一样
		parent[fp] = tp;
	}
	//查找x的代表节点
	private static int find(int[] parent, int x) {
		//x的备份，用于修改沿途的每个节点的根节点，相当于每次查找都减少并优化了路径
		int copyX = x;
		while(parent[x] != x) {
			x = parent[x];
		}
		//简短路径，将这个路径上所有的节点都直接指向代表节点
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
package question35;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import question34.Graph;
import question34.GraphGenerator;
import question34.Node;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	
	/**
	 * 较为简单
	 * 宽度优先遍历使用队列
	 * 注意使用辅助set来标记节点是否进过队列，进过队列的就不需要再进队列了，注意每个节点是出队列时访问
	 * @param root
	 */
	public static void BFS(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>();
		queue.offer(root);
		set.add(root);
		while(!queue.isEmpty()) {
			root = queue.poll();
			System.out.print(root.value + " ");
			for(Node node : root.nextNodes) {
				if(!set.contains(node)) {
					queue.offer(node);
					//每次在队列中加也要直接加入set
					set.add(node);
				}
			}
		}
		System.out.println();
	}
	
	
	
	/**
	 * 较为复杂
	 * 深度优先遍历使用栈
	 * 注意使用辅助set来标记节点是否进过栈
	 * @param root
	 */
	public static void DFS(Node root) {
		if(root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.push(root);
		set.add(root);
		System.out.print(root.value + " ");
		while(!stack.isEmpty()) {
			root = stack.pop();
			for(Node node : root.nextNodes) {
				if(!set.contains(node)) {
					stack.push(root);
					stack.push(node);
					set.add(node);
					System.out.print(node.value + " ");
					break;
				}
			}
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {
		int[][] matrix = {
				{0,0,0,0,0,0,0,0},
				{1,1,1,2,2,3,4,7},
				{2,3,4,3,7,5,6,3}
		};
		Graph graph = GraphGenerator.createGraph(matrix);
		Node root = graph.nodes.get(1);
		BFS(root);
		System.out.println("=============");
		DFS(root);
	}
}
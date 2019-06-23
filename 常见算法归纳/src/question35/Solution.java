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
	 * ��Ϊ��
	 * ������ȱ���ʹ�ö���
	 * ע��ʹ�ø���set����ǽڵ��Ƿ�������У��������еľͲ���Ҫ�ٽ������ˣ�ע��ÿ���ڵ��ǳ�����ʱ����
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
					//ÿ���ڶ����м�ҲҪֱ�Ӽ���set
					set.add(node);
				}
			}
		}
		System.out.println();
	}
	
	
	
	/**
	 * ��Ϊ����
	 * ������ȱ���ʹ��ջ
	 * ע��ʹ�ø���set����ǽڵ��Ƿ����ջ
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
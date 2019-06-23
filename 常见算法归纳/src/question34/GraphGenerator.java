package question34;

public class GraphGenerator {
	
	/**
	 * 面试题中通常要求这样创建图：
	 * 给出一个二维数组：
	 * [[2, 4, 7],
	 * [1, 3, 2],
	 * [2, 1, 3]]
	 * 分别表示边的权重，from节点，to节点
	 * @param matrix
	 * @return
	 */
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for(int j = 0; j < matrix[0].length; j++) {
			int weight = matrix[0][j];
			int from = matrix[1][j];
			int to = matrix[2][j];
			if(!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if(!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge edge = new Edge(weight, fromNode, toNode);
			fromNode.nextNodes.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(edge);
			graph.edges.add(edge);
			
		}
		return graph;
//		Graph graph = new Graph();
//		for (int i = 0; i < matrix.length; i++) {
//			Integer from = matrix[i][0];
//			Integer to = matrix[i][1];
//			Integer weight = matrix[i][2];
//			if (!graph.nodes.containsKey(from)) {
//				graph.nodes.put(from, new Node(from));
//			}
//			if (!graph.nodes.containsKey(to)) {
//				graph.nodes.put(to, new Node(to));
//			}
//			Node fromNode = graph.nodes.get(from);
//			Node toNode = graph.nodes.get(to);
//			Edge newEdge = new Edge(weight, fromNode, toNode);
//			fromNode.nextNodes.add(toNode);
//			fromNode.out++;
//			toNode.in++;
//			fromNode.edges.add(newEdge);
//			graph.edges.add(newEdge);
//		}
	}
}

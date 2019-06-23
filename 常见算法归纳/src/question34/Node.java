package question34;

import java.util.ArrayList;

public class Node {
	public int value;
	public int in;
	public int out;
	//表示从该节点出发能到达的节点
	public ArrayList<Node> nextNodes;
	//从该节点出发的边
	public ArrayList<Edge> edges;
	
	public Node(int value) {
		this.value = value;
		nextNodes = new ArrayList<>();
		edges = new ArrayList<>();
	}
	@Override
	public String toString() {
		return value + "";
	}
}

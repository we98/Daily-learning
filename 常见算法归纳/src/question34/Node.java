package question34;

import java.util.ArrayList;

public class Node {
	public int value;
	public int in;
	public int out;
	//��ʾ�Ӹýڵ�����ܵ���Ľڵ�
	public ArrayList<Node> nextNodes;
	//�Ӹýڵ�����ı�
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

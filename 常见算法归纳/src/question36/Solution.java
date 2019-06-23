package question36;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	
	public static List<Node> topologySort(Graph graph) {
		if(graph == null) {
			return null;
		}
		List<Node> result = new ArrayList<>();
		HashMap<Node, Integer> inMap = new HashMap<>();
		for(Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if(node.in == 0) {
				result.add(node);
			}
		}
		for(int i = 0; i < result.size(); i++) {
			for(Node node : result.get(i).nextNodes) {
				inMap.put(node, inMap.get(node)-1);
				if(inMap.get(node) == 0) {
					result.add(node);
				}
			}
		}
		return result;
		
	}
	
	
	public static void main(String[] args) {
		int[][] matrix = {
				{0,0,0,0,0,0,0,0},
				{1,1,1,2,2,3,4,7},
				{2,3,4,3,7,5,6,3}
		};
		Graph graph = GraphGenerator.createGraph(matrix);
		System.out.println(topologySort(graph));
	}
}
package treewidth;

import java.util.ArrayList;

public class Node {
	
	private int index;
	private ArrayList<Node> neighbors;
	
	public Node(int index) {
		this.index = index;
		neighbors = new ArrayList<Node>();
	}
	
	public int getIndex() {
		return index;
	}
	
	public void addNeighbor(Node node) {
		neighbors.add(node);
	}
	
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	


}

package treewidth;

import java.util.ArrayList;

public class Bag {
	
	private ArrayList<Node> nodes;
	
	public Bag() {
		nodes = new ArrayList<Node>();
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}

}

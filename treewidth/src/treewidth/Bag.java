package treewidth;

import java.util.ArrayList;

public class Bag {
	
	private int index;
	private ArrayList<Bag> children;
	private ArrayList<Node> nodes;
	private int[] indSets;
	
	public Bag(int index, int treeWidth) {
		this.index = index;
		this.children = new ArrayList<Bag>();
		this.nodes = new ArrayList<Node>();
		indSets = new int[2^(treeWidth+1)];
	}
	
	public ArrayList<Node> nodes() {
		return nodes;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void addChild(Bag bag) {
		children.add(bag);
	}

}

package treewidth;

import java.util.ArrayList;

public class TDNode {
	
	private ArrayList<TDNode> children;
	private ArrayList<Node> bag;
	private int[] indSets;
	
	public TDNode(int w) {
		this.children = new ArrayList<TDNode>();
		this.bag = new ArrayList<Node>();
		indSets = new int[2^(w+1)];
	}
	
	public ArrayList<Node> getBag() {
		return bag;
	}
	
	public void setBag(Node node){
		bag.add(node);
	}

}

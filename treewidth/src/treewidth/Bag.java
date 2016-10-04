package treewidth;

import java.util.ArrayList;

public class Bag {
	
	private int index;
	private ArrayList<Bag> children;
	private ArrayList<Node> nodes;
	private ArrayList<ArrayList<Node>> independentSets;
	
	public Bag(int index, int treeWidth) {
		this.index = index;
		this.children = new ArrayList<Bag>();
		this.nodes = new ArrayList<Node>();
		//indSets = new int[2^(treeWidth+1)];
		independentSets = new ArrayList<ArrayList<Node>>();
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
	
	public int childCount() {
		return children.size();
	}
	
	public ArrayList<Bag> getChildren() {
		return children;
	}
	
	public void calculateAllIndependentSets() {
		ArrayList<Node> ignore = new ArrayList<Node>();
		ArrayList<Node> IS = new ArrayList<Node>();
		goDeeper(IS, ignore);
	}
	
	private void goDeeper(ArrayList<Node> IS, ArrayList<Node> ignore) {
		
		if(IS.size() + ignore.size() >= nodes.size()) {
			return;
		}
		
		ArrayList<Node> options = (ArrayList<Node>) nodes.clone();
		options.removeAll(IS);
		options.removeAll(ignore);			
		
		for(Node option : options) {
			ArrayList<Node> newIS = (ArrayList<Node>) IS.clone();
			ArrayList<Node> newIgnore = (ArrayList<Node>) ignore.clone();
			newIS.add(option);
			newIgnore.addAll(option.getNeighbors());
			independentSets.add(newIS);
			goDeeper(newIS, newIgnore);
		}

		
	}

}

package treewidth;

import java.util.ArrayList;
import java.util.HashSet;

public class Bag {
	
	private int index;
	private ArrayList<Bag> children;
	private NodeSet nodes;
	private HashSet<NodeSet> independentSets;
	
	public Bag(int index, int treeWidth) {
		this.index = index;
		this.children = new ArrayList<Bag>();
		this.nodes = new NodeSet();
		independentSets = new HashSet<NodeSet>();
	}
	
	public NodeSet nodes() {
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
	
	public String toString() {
		return "Bag " + index;
	}
	
	public void calculateAllIndependentSets() {
		NodeSet ignore = new NodeSet();
		NodeSet IS = new NodeSet();
		goDeeper(IS, ignore);
	}
	
	private void goDeeper(NodeSet IS, NodeSet ignore) {		
		//Base case
		NodeSet calcIgnore = (NodeSet) ignore.clone();
		calcIgnore.removeAll(nodes);
		int nonIgnoreCount = calcIgnore.size();
		if(IS.size() + (ignore.size() - nonIgnoreCount) >= nodes.size()) {
			return;
		}
		
		NodeSet options =  (NodeSet) nodes.clone();
		options.removeAll(IS);
		options.removeAll(ignore);			
		
		for(Node option : options) {
			NodeSet newIS = (NodeSet) IS.clone();
			NodeSet newIgnore = (NodeSet) ignore.clone();
			newIS.add(option);
			newIgnore.addAll(option.getNeighbors());
			independentSets.add(newIS);
			goDeeper(newIS, newIgnore);
		}

		
	}

}



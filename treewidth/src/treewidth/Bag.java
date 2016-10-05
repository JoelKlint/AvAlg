package treewidth;

import java.util.ArrayList;
import java.util.HashSet;

public class Bag {
	
	private int index;
	private ArrayList<Bag> children;
	private NodeSet nodes;
	private NodeSet[] independentSets;
	private int[] ft;
	
	public Bag(int index, int treeWidth) {
		this.index = index;
		this.children = new ArrayList<Bag>();
		this.nodes = new NodeSet();
		//independentSets = new HashSet<NodeSet>();
		//graphIndependentSets = new HashSet<NodeSet>();
	}
	
	public NodeSet getNodes() {
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
	
	public boolean hasNoChildren() {
		return children.isEmpty();
	}
	
	public NodeSet[] getIndependentSets() {
		return independentSets;
	}
	
	public int getFt(NodeSet set) {
		int index = findIndexOfNodeSet(set);
		return ft[index];
	}
	
	public void setFt(NodeSet set, int value) {
		int index = findIndexOfNodeSet(set);
		ft[index] = value;
	}
	
	private int findIndexOfNodeSet(NodeSet set) {
		for(int i = 0; i < independentSets.length; i++) {
			if(independentSets[i].equals(set)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getMaxIndependentSet() {
		int max = 0;
		for( int ftu : ft ) {
			if(ftu > max) {
				max = ftu;
			}
		}
		return max;
	}
	
	public void calculateAllIndependentSets() {
		HashSet<NodeSet> currentIndependentSets = new HashSet<NodeSet>();
		NodeSet ignore = new NodeSet();
		NodeSet IS = new NodeSet();
		goDeeper(IS, ignore, currentIndependentSets);
		independentSets = new NodeSet[currentIndependentSets.size()];
		currentIndependentSets.toArray(independentSets);
		ft = new int[independentSets.length];
		
	}
	
	private void goDeeper(NodeSet IS, NodeSet ignore, HashSet<NodeSet> currentIndependentSets) {		
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
			currentIndependentSets.add(newIS);
			goDeeper(newIS, newIgnore, currentIndependentSets);
		}

		
	}

}



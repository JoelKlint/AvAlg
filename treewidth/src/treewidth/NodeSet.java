package treewidth;

import java.util.HashSet;

public class NodeSet extends HashSet<Node>{
	
	public NodeSet() {
		super();
	}
	
	public NodeSet intersection(NodeSet set) {
		NodeSet intersection = (NodeSet) this.clone();
		intersection.retainAll(set);
		return intersection;
	}
	
	public boolean equals(HashSet<Node> set2) {
		return this.containsAll(set2) && set2.containsAll(this);
	}

}

package treewidth;

public class TreeDecompositionGraph {
	
	private Bag[] bags;
	private Bag root;
	int width;
	
	public TreeDecompositionGraph(int nbrOfBags, int width) {
		this.width = width;
		bags = new Bag[nbrOfBags + 1];
		for(int i = 0; i <= nbrOfBags; i++) {
			bags[i] = new Bag(i, width);
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public Bag getBag(int index) {
		if(index == 0) {
			System.out.println("Can not get bag 0 from tree decomposition graph");
			return null;
		}
		return bags[index];
	}
	
	public Bag getRoot() {
		//If there is only one bag. Index zero is empty
		if(bags.length == 2) {
			root = bags[1];
			removeSelfFromChildren(root);
			return root;
		}
		for(Bag bag : bags) {
			if(bag.childCount() == 1) {
				root = bag;
				removeSelfFromChildren(root);
				return root;
			}
		}
		return null;
	}
	
	private void removeSelfFromChildren(Bag bag) {
		for(Bag child : bag.getChildren()) {
			child.removeChild(bag);
			removeSelfFromChildren(child);
		}
	}
	
	public void calculateAllIS() {
		for(Bag bag : bags) {
			bag.calculateAllIndependentSets();
		}
	}

}

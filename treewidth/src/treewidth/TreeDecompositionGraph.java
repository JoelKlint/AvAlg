package treewidth;

public class TreeDecompositionGraph {
	
	private Bag[] bags;
	
	public TreeDecompositionGraph(int nbrOfBags, int treeWidth) {
		bags = new Bag[nbrOfBags + 1];
		for(int i = 0; i <= nbrOfBags; i++) {
			bags[i] = new Bag(i, treeWidth);
		}
	}
	
	public Bag getBag(int index) {
		if(index == 0) {
			System.out.println("Can not get bag 0 from tree decomposition graph");
			return null;
		}
		return bags[index];
	}

}

package treewidth;

public class Algorithm {
	
	private Graph graph;
	private TreeDecompositionGraph tree;
	
	public Algorithm(Graph graph, TreeDecompositionGraph tree) {
		this.graph = graph;
		this.tree = tree;
	}
	
	public void calculate() {
		Bag rootBag = tree.getRoot();
		
	}
	
	private void visitBag(Bag bag) {
		for(Bag child : bag.getChildren()) {
			visitBag(child);
		}
				
	}
	

}

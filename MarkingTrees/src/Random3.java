
public class Random3 extends Random2 {
	
	private Tree tree;
	
	public Random3(Tree tree) {
		super(tree.count);
		this.tree = tree;
	}

	public int nextInt() {
		int currentRand = super.nextInt();
		while(tree.nodes[currentRand]) {
			currentRand = super.nextInt();
		}
		
		return currentRand;
	}
}

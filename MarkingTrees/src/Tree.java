public class Tree {
	
	public boolean[] nodes;
	private int count;
	
	public Tree(int count) {
		this.count = count;
		nodes = new boolean[count + 1];
	}
	
	public int markNode(int index) {
		boolean[] visited = nodes;
		return markRecursive(index, visited);
	}
	
	public boolean allMarked() {
		for(int i = 1; i < nodes.length; i++) {
			if(nodes[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	private int markRecursive(int index, boolean[] visited) {
		if(index == 0) {
			return 1;
		}
		//calculate indexes
		int parent = index / 2;
		int leftChild = index * 2;
		int rightChild = leftChild + 1;
		int sibling = index % 2 == 0 ? index + 1 : index - 1;
		
		if(parent > count) {
			parent = 0;
		}
		if(leftChild > count) {
			leftChild = 0;
		}
		if(rightChild > count) {
			rightChild = 0;
		}
		if(sibling > count) {
			sibling = 0;
		}
		
		
		//Start marking
		nodes[index] = true;
		visited[index] = true;
		
		if( nodes[sibling] && !visited[parent]) {
			markRecursive(parent, visited);
		}
		if( nodes[parent] && !visited[sibling]) {
			markRecursive(sibling, visited);
		}
		if( nodes[leftChild] && !visited[rightChild]) {
			markRecursive(rightChild, visited);
		}
		else if( nodes[rightChild] && !visited[leftChild]) {
			markRecursive(leftChild, visited);
		}
		
		return 1;
		
	}

}

package treewidth;

public class Graph {
	
	private Node[] nodes;
	
	public Graph(int nbrOfNodes) {
		nodes = new Node[nbrOfNodes+1];
		for(int i = 0; i <= nbrOfNodes; i++) {
			nodes[i] = new Node(i);
		}
	}
	
	public Node getNode(int index) {
		if(index == 0) {
			System.out.println("Can not get node 0 from graph");
			return null;
		}
		return nodes[index];
	}
	
	public int getNumberOfVertices() {
		return nodes.length;
	}

}

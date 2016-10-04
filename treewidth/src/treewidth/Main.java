package treewidth;

import java.io.IOException;

public class Main {
	public static void main(String[]args) throws IOException {
		
		Parser parser = new Parser();
		String fileName = "wedge";
		Graph graph = parser.parseGraph("./data/" + fileName + ".gr");
		TreeDecompositionGraph tree = parser.parseTree("./data/" + fileName + ".td", graph);
		
	}

}

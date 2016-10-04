package treewidth;

import java.io.IOException;

public class Main {
	public static void main(String[]args) throws IOException {
		
		Parser parser = new Parser();
		Graph graph = parser.parseGraph("./data/web4.gr");
		
	}

}

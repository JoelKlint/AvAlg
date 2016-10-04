package treewidth;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	
	BufferedReader reader;
	
	public Parser() {
		
	}
	
	public Graph parseGraph(String path) throws IOException {
		System.out.println("Started parsing graph");
		Graph graph = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			String[] splitLine  = reader.readLine().split(" ");
			int nbrOfVertices = Integer.parseInt(splitLine[2]);
			int nbrOfEdges = Integer.parseInt(splitLine[3]);
			
			graph = new Graph(nbrOfVertices);
			
			String line = reader.readLine();
			
			while(line != null) {
				splitLine = line.split(" ");
				//Skip comments
				if(splitLine[0] == "c") {
					continue;
				}
				
				int node1index = Integer.parseInt(splitLine[0]);
				int node2index = Integer.parseInt(splitLine[1]);
				Node node1 = graph.getNode(node1index);
				Node node2 = graph.getNode(node2index);
				node1.addNeighbor(node2);
				node2.addNeighbor(node1);		
				
				line = reader.readLine();
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find input file");
			e.printStackTrace();
		}
		return graph;
	}
	
	public void parseTree(String path) throws IOException {
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			String[] firstLine = line.split(" ");
			int nbrOfBags = Integer.parseInt(firstLine[2]);
			int width = Integer.parseInt(firstLine[3]) - 1;
			int nbrOfVertices = Integer.parseInt(firstLine[4]);
			
			line = reader.readLine();
			String [] bagLine = line.split(" ");
			
			ArrayList<TDNode> treeDecomp = new ArrayList<TDNode>();
			
			while (bagLine[0] == "b"){
				TDNode tdNode = new TDNode(width);
				
				//tdNode.addNodeToBag()
				
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find input file");
			e.printStackTrace();
		}
	}
	

}

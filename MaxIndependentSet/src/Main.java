import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Main {
	
	private static int callCount = -1;
	public static void main (String[]args) throws IOException {
		
		Parser parser = new Parser("./data/g70.in");
		int[][] matrix = parser.parse();
		
		int[][] test = {
				{0,1,1,0,0},
				{1,0,1,1,0},
				{1,1,0,1,0},
				{0,1,1,0,1},
				{0,0,0,1,0}
		};		
		
		int MIS = recurse(matrix);
		System.out.println("MIS is: " + MIS);
		System.out.println("Call coult is: " + callCount);
	}
	
	private static int recurse(int[][] matrix) {
		callCount++;
		if(graphIsEmpty(matrix)) {
			return 0;
		}
		
		int nodeWithTwoNeighbours = -1;
		if( (nodeWithTwoNeighbours = findNodeWithXAmountOfNeighbours(matrix, 2)) > -1 ) {
			if( neighboursOfNodeHasEdge(matrix, nodeWithTwoNeighbours) ) {
				int[][] newGraph = removeNeighboursAndSelfFromGraph(matrix, nodeWithTwoNeighbours);
				return 1 + recurse(newGraph);
			}
			else {
				int[][] newGraph = addNewNodeWithNeighbours(matrix, nodeWithTwoNeighbours);
				return 1 + recurse(newGraph);
			}
		}
		
		int nodeWithOneNeighbour = -1;
		if( (nodeWithOneNeighbour = findNodeWithXAmountOfNeighbours(matrix, 1)) > -1 ) {
			int[][] newGraph = removeNeighboursAndSelfFromGraph(matrix, nodeWithOneNeighbour);
			return 1 + recurse(newGraph);
		}
		
		int neighbourlessNode = -1;
		if( (neighbourlessNode = findNodeWithXAmountOfNeighbours(matrix, 0)) > -1 ) {
			int[][] newGraph = removeNodeFromGraph(matrix, neighbourlessNode);
			return 1 + recurse(newGraph);
		}
		
		int node = highestDegreeNode(matrix);
		int[][] graphWithoutNode = removeNodeFromGraph(matrix, node);
		int[][] graphWithoutNeighbours = removeNeighboursAndSelfFromGraph(matrix, node);
		int withoutNode = recurse(graphWithoutNode);
		int withoutNeighbours = 1 + recurse(graphWithoutNeighbours);
		return withoutNode > withoutNeighbours ? withoutNode : withoutNeighbours;		
	}
	
	private static boolean neighboursOfNodeHasEdge(int[][] matrix, int node) {
		ArrayList<Integer> nodeNeighbours = new ArrayList<Integer>();
//		Add all neighbours
		for(int col = 0; col < matrix.length; col++) {
			if(matrix[node][col] != 0) {
				nodeNeighbours.add(col);
			}
		}
		int neighbour1 = nodeNeighbours.remove(0);
		int neighbour2 = nodeNeighbours.remove(0);
		if( matrix[neighbour1][neighbour2] == 1 ) {
			return true;
		}
		return false;
	}
	
	public static int findNodeWithXAmountOfNeighbours(int[][] matrix, int neighborCount) {
		for(int row = 0; row < matrix.length; row++) {
			int edges = 0;
			for(int col = 0; col < matrix.length; col++) {
				edges += matrix[row][col];
			}
			if(edges == neighborCount) {
				return row;
			}
		}
		return -1;
	}
	
	private static int[][] addNewNodeWithNeighbours(int[][] matrix, int node) {
		ArrayList<Integer> nodeNeighbours = new ArrayList<Integer>();
//		Add all neighbours
		for(int col = 0; col < matrix.length; col++) {
			if(matrix[node][col] != 0) {
				nodeNeighbours.add(col);
			}
		}
		
		HashSet<Integer> zNeighbours = new HashSet<Integer>();
		for(int neighbor : nodeNeighbours) {
			for(int col = 0; col < matrix.length; col++) {
				if(matrix[neighbor][col] != 0) {
					zNeighbours.add(col);
				}
			}
		}
		zNeighbours.remove(node);
		
//		Create new graph
		int[][] newGraph = new int[matrix.length+1][matrix.length+1];
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix.length; col++) {
				newGraph[row][col] = matrix[row][col];
			}
		}
//		Add new node to new graph
		
		for(int zNeighbour : zNeighbours) {
			newGraph[newGraph.length-1][zNeighbour] = 1;
			newGraph[zNeighbour][newGraph.length-1] = 1;
		}
		
//		Remove nodes neighbours
		newGraph = removeNeighboursAndSelfFromGraph(newGraph, node);		
		return newGraph;
	}
	
	private static int[][] removeNodeFromGraph(int[][] matrix, int indexSkip) {
		int[][] newGraph = new int[matrix.length-1][matrix.length-1];
		for(int row = 0; row < matrix.length; row++) {
			if (row < indexSkip) { 
				for(int col = 0; col < matrix.length; col++) {
					if(col < indexSkip) {
						newGraph[row][col] = matrix[row][col];
					}
					if(col > indexSkip) {
						newGraph[row][col-1] = matrix[row][col];
					}
				}
			}
			else if (row > indexSkip) {
				for(int col = 0; col < matrix.length; col++) {
					if(col < indexSkip) {
						newGraph[row-1][col] = matrix[row][col];
					}
					if(col > indexSkip) {
						newGraph[row-1][col-1] = matrix[row][col];
					}
				}
			}
		}
		return newGraph;
	}
	
	private static int[][] removeNeighboursAndSelfFromGraph(int[][] matrix, int nodeIndex) {
		ArrayList<Integer> neighbours = new ArrayList<Integer>();
		neighbours.add(nodeIndex);
		for(int col = 0; col < matrix.length; col++) {
			if(matrix[nodeIndex][col] != 0) {
				neighbours.add(col);
			}
		}
		Collections.sort(neighbours);
		Collections.reverse(neighbours);
		
		int[][] newGraph = matrix;
		
		for(int neighbor : neighbours) {
			newGraph = removeNodeFromGraph(newGraph, neighbor);
		}
	
		return newGraph;
	}
	
	private static boolean graphIsEmpty(int[][] matrix) {
		return matrix.length == 0;
	}
	
	private static void printMatrix(int[][] matrix) {
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}
	
	private static int highestDegreeNode(int[][] matrix) {
		int currentIndex = -1;
		int currentMax = 0;
		for(int row = 0; row < matrix.length; row++) {
			int rowSum = IntStream.of(matrix[row]).sum();
			if( rowSum > currentMax ) {
				currentIndex = row;
				currentMax = rowSum;
			}
		}
		return currentIndex;
	}
}

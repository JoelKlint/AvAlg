public class Graph {
	private int[][] data;
	
	public Graph (int [][] data){
		this.data = data;
	}
	
	public boolean isEmpty() {
		for( int[] row : data ) {
			for( int value : row ) {
				if(value == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int loneNeighbor() {
		//For each row
		for(int rowIndex = 0; rowIndex < data[0].length; rowIndex++ ) {
			int[] row = data[rowIndex];
			for( int edge : row ) {
				//Om edge == 1 
				//om edge == 0 continue
			}
		}
 		
		return 0;
	}

//	public void IndependentSet 
}

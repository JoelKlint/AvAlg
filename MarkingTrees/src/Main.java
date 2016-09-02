import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) {		
		
		try {
		//	int[] values = {3,7,15,31,63,127,255,511,1023,2047,4095,8191,16383,32767,65536,131071,262143,524287,1048575};
		//	int[] values = {3, 7, 15, 31, 63};
			int[] values = {255};
			int nbrOfTests = 10;
			
			PrintWriter pw = new PrintWriter("result.csv", "UTF-8");

			pw.print(",");
			for(int i = 1; i<= nbrOfTests; i++) {
				pw.print(i + ",");
			}
			pw.println();			
			
			for (int size : values) {
				System.out.println("Starting with " + size);
				pw.print(size + ",");
				
				//Array of results
				int[] result = new int[nbrOfTests + 1];
				
				//Run j amount of tests
				for (int j = 1; j <= nbrOfTests; j++){
					//Create a new tree
					Tree tree = new Tree(size);
					
				//	Random rand = new Random();
				//	Random2 rand2 = new Random2(size);
					Random3 rand3 = new Random3(tree);
					
					//Mark all nodes
					while( !tree.allMarked() ) {
						
					//	int random = rand.nextInt(size) + 1;
					//	int random = rand2.nextInt();
						int random = rand3.nextInt();
						tree.markNode(random);
						
						result[j]++;
					}
					//Append current result to file
					pw.print(result[j] + ",");
				}
				//Write new line to file
				pw.println();
			}
			//Save file
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All nodes marked");
	}

}

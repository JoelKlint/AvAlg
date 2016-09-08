import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) {		
		
		try {
		//	int[] values = {3,7,15,31,63,127,255,511,1023,2047,4095,8191,16383,32767,65535,131071,262143,524287,1048575};
			int[] values = {3,7,15,31,63,127,255,511,1023,2047,4095,8191};
			int nbrOfTests = 10;
			
			PrintWriter pw = new PrintWriter("result.csv", "UTF-8");

			pw.print(",");
			for(int i = 1; i<= nbrOfTests; i++) {
				pw.print(i + ",");
			}
			pw.println();
			
			for(int currentRand = 1; currentRand <= 3; currentRand++) {
				
				System.out.println("current rand is " + currentRand);				
				
				for (int size : values) {
					System.out.println("Starting with " + size);
					pw.print("rand " + currentRand + " @ size " + size + ",");
					
					//Array of results
					int[] result = new int[nbrOfTests + 1];
					
					//Run j amount of tests
					for (int j = 1; j <= nbrOfTests; j++){
						//Create a new tree
						Tree tree = new Tree(size);
						
						Random1 rand = null;
						switch(currentRand) {
							case 1:	rand = new Random1(size);
									break;
							case 2:	rand = new Random2(size);
									break;
							case 3: rand = new Random3(tree);
									break;
							default:break;
						}
						
						//Mark all nodes
						while( !tree.allMarked() ) {
							int random = rand.nextInt();
							tree.markNode(random);
							
							result[j]++;
						}
						//Append current result to file
						pw.print(result[j] + ",");
						System.out.print(result[j] + ",");
					}
					//Write new line to file
					pw.println();
					System.out.println();	
					
				}
				
			}
			//Save file
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("All nodes marked");
	}

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	
	BufferedReader reader;
	
	public Parser(String path) throws IOException {
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find input file");
			e.printStackTrace();
		}
	}
	
	public int[][] parse() throws IOException {
		String line = reader.readLine();
		int nodeCount = Integer.parseInt(line);
		int result[][] = new int[nodeCount][nodeCount];		
		
		
		int currentRow = 0;
		while( (line = reader.readLine()) != null) {
			String[] values = line.split(" ");
			for(int column = 0; column < nodeCount; column++) {
				int value = Integer.parseInt(values[column]);
				result[currentRow][column] = value;
			}
			currentRow++;
		}
		
		return result;		
	}
	
	

}

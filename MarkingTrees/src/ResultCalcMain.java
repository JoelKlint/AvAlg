import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ResultCalcMain {
	public static void main(String[]args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Scanner scan = new Scanner(new File("result.csv"));
		scan.nextLine();
		PrintWriter pw = new PrintWriter("resultCalculated.csv", "UTF-8");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] stringValues = line.split(",");
			
			int valueCount = stringValues.length - 1;
			
			int[] values = new int[valueCount];
			for(int index = 0; index < valueCount; index++) {
				values[index] = Integer.parseInt(stringValues[index + 1]);
			}
			double mean = ResultCalculator.meanValue(values);
			double standardDeviation = ResultCalculator.deviation(values);
			pw.println(stringValues[0] + "," + mean + " ± " + standardDeviation);			
		}
		pw.close();
		System.out.println("done");
		
	}

}


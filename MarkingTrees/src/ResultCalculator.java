public class ResultCalculator {
	
	public static double meanValue(int[] values) {
		double sum = 0;
		for( double value : values ) {
			sum+=value;
		}
		return sum/values.length;
	}
	
	public static double deviation(int[] values) {
		double mean = meanValue(values);
		double sum = 0;
		for( int value : values ) {
			sum += Math.pow(value - mean, 2);
		}
		return Math.sqrt(sum/values.length);		
	}

}
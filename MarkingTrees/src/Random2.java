import java.util.Random;
import java.util.Stack;

public class Random2 {
	
	public Stack<Integer> pool;
	
	public Random2(int max) {
		int[] values = new int[max];
		for(int i = 0; i < max; i++) {
			values[i] = i + 1;
		}
		values = shuffleArray(values);		
		pool = new Stack<Integer>();
		for( int value : values ) {
			pool.push(value);
		}		
	}
	
	public int nextInt() {		
		return pool.pop();
	}
	
	private int[] shuffleArray(int[] array) {		
		Random rand = new Random();
		for(int i = array.length - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}

}

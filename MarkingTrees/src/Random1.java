import java.util.Random;

public class Random1 {
	
	private Random rand;
	private int max;
	
	public Random1(int max) {
		this.rand = new Random();
		this.max = max;
	}
	
	public int nextInt() {		
		return rand.nextInt(max) + 1;
	}

}

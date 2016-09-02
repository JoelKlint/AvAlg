import java.util.Random;

public class Main {
	public static void main(String[] args) {
		
		int size = 1000;
		Tree tree = new Tree(size);
		
		Random rand = new Random();
		while( !tree.allMarked() ) {
			int random = rand.nextInt(size) + 1;
			tree.markNode(random);
		}
		System.out.println("All nodes marked");
	}

}

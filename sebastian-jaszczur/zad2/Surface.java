import java.util.Random;


public class Surface {
	
	private Random randGen = new Random();
	private int turn = 0;
	
	public Surface(int xSize, int ySize, Field defaultField, Field[] otherFields) {
		
	}

	public boolean alive() {
		
		return (randGen.nextInt(50)!=17);
	}

	public void run() {
		turn += 1;
		
	}
	
	public String toString() {
		return "Surface: "+turn;
	}

}

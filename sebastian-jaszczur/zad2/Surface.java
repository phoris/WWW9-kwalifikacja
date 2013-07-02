import java.util.Random;


public class Surface {
	
	private Random randGen = new Random();
	private int turn = 0;
	private Field area[][];
	
	public Surface(int xSize, int ySize, Field[] otherFields) {
		area = new Field[xSize][ySize];
		for(int i=0; i<xSize; i++)
			for(int j=0; j<ySize; j++)
				area[i][j] = new Field();
		for(Field f: otherFields) {
			int x, y;
			do {
				x = randGen.nextInt(xSize);
				y = randGen.nextInt(ySize);
			}while(area[x][y].getClass() != Field.class);
			
			area[x][y] = f;
		}
	}

	public boolean alive() {
		
		return (randGen.nextInt(50)!=17);
	}

	public void run() {
		turn += 1;
		
	}
	
	public String toString() {
		String str = "Turn number: "+turn+"\n";
		for(Field[] x: area) {
			for(Field y: x)
				str += y;
			str += "\n";
		}
		return str;
	}

}

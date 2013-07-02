import java.util.Random;


public class Surface {
	
	private Random randGen = new Random();
	private int turn = 0;
	private Field area[][];
	private int xS, yS;
	
	public Surface(int xSize, int ySize, Field[] otherFields) {
		xS = xSize;
		yS = ySize;
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
			f.setPosition(x, y);
			f.setDirection(Direction.randomDirection());
			area[x][y] = f;
		}
	}
	
	private int numberOfAliveObjects() {
		int objectsAlive = 0;
		for(Field[] row: area) {
			for(Field x: row) {
				if(x.alive()>0) {
					objectsAlive += 1;
				}
			}
		}
		return objectsAlive;
	}

	public boolean alive() {
		return(numberOfAliveObjects()>1);
	}

	public void run() {
		turn += 1;
		for(Field[] row: area) {
			for(Field x: row) {
				x.interact(this, turn, area);
			}
		}
	}
	
	public String toString() {
		String str = "Turn number: "+turn+"\n"+
						"Bots alive: "+numberOfAliveObjects()+"\n";
		for(int i=yS-1; i>=0; i--) {
			for(int j=0; j<xS; j++) {
				str += area[j][i];
			}
			str += "\n";
		}
		return str;
	}

}

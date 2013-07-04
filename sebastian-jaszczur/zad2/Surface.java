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
	
	private int numberOfAliveObjects(boolean wypisuj) {
		int objectsAlive = 0;
		for(Field[] row: area) {
			for(Field x: row) {
				if(x.alive()>0) {
					objectsAlive += 1;
					if(wypisuj) {
						System.out.println(x+" hp is "+x.alive());
					}
				}
			}
		}
		return objectsAlive;
	}

	public boolean alive() {
		return(numberOfAliveObjects(false)>1);
	}

	private int turnsPerTurn = 0;
	public void run() {
		turn += 1;
		if(turnsPerTurn == 0)
		{
			for(Field[] row: area) {
				for(Field x: row) {
					turnsPerTurn = Math.max(turnsPerTurn, x.interact(this, turn, area));
				}
			}
		}
		for(Field[] row: area) {
			for(Field x: row) {
				x.doOrders(this, turn, area);
			}
		}
		turnsPerTurn -= 1;
	}
	
	public String toString() {
		String str = "Turn number: "+turn+"\n"+
						"Bots alive: "+numberOfAliveObjects(true)+"\n";
		
		for(int i=yS-1; i>=0; i--) {
			for(int j=0; j<xS; j++) {
				str += area[j][i];
			}
			str += "\n";
		}
		return str;
	}

}

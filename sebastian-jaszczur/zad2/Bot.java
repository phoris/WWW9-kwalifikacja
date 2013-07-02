
public class Bot extends Field {
	
	private char name;
	private int hp = 5;
	private Direction dir;
	private int x, y;
	//private BotAI control;
	private static int actions = 5;
	
	public Bot(char c) {
		name = c;
	}
	
	public int alive() {
		return hp;
	}
	
	public void getHit() {
		hp -= 1;
	}
	
	public void setPosition(int xP, int yP) {
		x = xP;
		y = yP;
	}
	
	public void setDirection(Direction d) {
		dir = d;
	}
	
	private int lastTurn = 0;
	public void interact(int turn, Field[][] area) {
		if(turn == lastTurn)
			return;
		lastTurn = turn;
		stepForward(area);
	}
	
	private void shot(Field[][] area) {
		switch(dir) {
		case UP:
		case DOWN:
			for(int i=0; i<area[0].length; i++) {
				if(i == y)
					continue;
				area[x][i].getHit();
			}
			break;
		case RIGHT:
		case LEFT:
			for(int i=0; i<area.length; i++) {
				if(i == x)
					continue;
				area[i][y].getHit();
			}
			break;
		}
	}
	
	private void chop(Field[][] area) {
		switch(dir) {
		case UP:
			area[x-1][y].getHit();
			area[x+1][y].getHit();
			area[x][y+1].getHit();
			break;
		case RIGHT:
			area[x][y+1].getHit();
			area[x][y-1].getHit();
			area[x+1][y].getHit();
			break;
		case LEFT:
			area[x][y+1].getHit();
			area[x][y-1].getHit();
			area[x-1][y].getHit();
			break;
		case DOWN:
			area[x-1][y].getHit();
			area[x+1][y].getHit();
			area[x][y-1].getHit();
			break;
		}
	}
	
	private void changePosition(Field[][] area, int newX, int newY) {
		if(newX>=area.length)newX -= area.length;
		if(newX<0)newX += area.length;
		if(newY>=area[0].length)newY -= area[0].length;
		if(newY<0)newY += area[0].length;
		
		if(area[newX][newY].getClass()==Field.class) {
			Field swapper = this;
			area[x][y] = area[newX][newY];
			area[newX][newY] = swapper;
			setPosition(newX, newY);
		}else
			System.out.println("WTF");
	}
	
	private void stepForward(Field[][] area) {
		switch(dir) {
		case UP:
			changePosition(area, x, y+1);
			break;
		case DOWN:
			changePosition(area, x, y-1);
			break;
		case LEFT:
			changePosition(area, x-1, y);
			break;
		case RIGHT:
			changePosition(area, x+1, y);
			break;
		}
	}
	
	private void stepBack(Field[][] area) {
		switch(dir) {
		case UP:
			changePosition(area, x, y-1);
			break;
		case DOWN:
			changePosition(area, x, y+1);
			break;
		case LEFT:
			changePosition(area, x+1, y);
			break;
		case RIGHT:
			changePosition(area, x-1, y);
			break;
		}
	}
	
	private void turnLeft(Field[][] area) {
		switch(dir) {
		case UP:
			dir = Direction.LEFT;
			break;
		case DOWN:
			dir = Direction.RIGHT;
			break;
		case LEFT:
			dir = Direction.DOWN;
			break;
		case RIGHT:
			dir = Direction.UP;
			break;
		}
	}
	
	private void turnRight(Field[][] area) {
		switch(dir) {
		case UP:
			dir = Direction.RIGHT;
			break;
		case DOWN:
			dir = Direction.LEFT;
			break;
		case LEFT:
			dir = Direction.UP;
			break;
		case RIGHT:
			dir = Direction.DOWN;
			break;
		}
	}

	public String toString() {
		switch(dir) {
		case UP:
			return "^"+name;
		case DOWN:
			return "v"+name; 
		case LEFT:
			return "<"+name; 
		case RIGHT:
			return ">"+name; 
		default:
			return "?E"; // this shouldn't happen
		}
	}
}

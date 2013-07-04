
public class Bot extends Field {
	
	private char name;
	private int hp = 5;
	private Direction dir;
	private int x, y;
	private BotAI control;
	private static int actions = 5;
	
	public Bot(char c, BotAI aI) {
		name = c;
		control = aI;
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
	
	private Field areaAt(Field[][] area, int x, int y) {
		if(x>=area.length)x -= area.length;
		if(x<0)x += area.length;
		if(y>=area[0].length)y -= area[0].length;
		if(y<0)y += area[0].length;
		return area[x][y];
	}
	
	private int lastTurn = 0;
	private Decision[] decs;
	private int orderNumber;
	public int interact(Surface surf, int turn, Field[][] area) {
		if(turn == lastTurn)
			return 0;
		lastTurn = turn;
		decs = control.decide(x, y, area, actions);
		orderNumber = 0;
		return Math.min(decs.length, actions);
	}
	
	private int lastActionTurn = 0;
	public void doOrders(Surface surf, int turn, Field[][] area) {
		if(lastActionTurn == turn)
			return;
		lastActionTurn = turn;
		if(orderNumber >= decs.length || orderNumber >= actions)
			return;
		switch(decs[orderNumber]) {
		case SHOT:
			shot(area);
			break;
		case CHOP:
			chop(area);
			break;
		case STEP:
			stepForward(area);
			break;
		case BACKSTEP:
			stepBack(area);
			break;
		case LEFT:
			turnLeft(area);
		case RIGHT:
			turnRight(area);
		}
		orderNumber += 1;
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
			areaAt(area, x-1, y).getHit();
			areaAt(area, x+1, y).getHit();
			areaAt(area, x, y+1).getHit();
			break;
		case RIGHT:
			areaAt(area, x, y+1).getHit();
			areaAt(area, x, y-1).getHit();
			areaAt(area, x+1, y).getHit();
			break;
		case LEFT:
			areaAt(area, x, y+1).getHit();
			areaAt(area, x, y-1).getHit();
			areaAt(area, x-1, y).getHit();
			break;
		case DOWN:
			areaAt(area, x-1, y).getHit();
			areaAt(area, x+1, y).getHit();
			areaAt(area, x, y-1).getHit();
			break;
		}
	}
	
	private void changePosition(Field[][] area, int newX, int newY) {
		newX = (newX+area.length)%area.length;
		newY = (newY+area[0].length)%area[0].length;
		
		if(area[newX][newY].getClass()==Field.class) {
			Field swapper = area[x][y];
			area[x][y] = area[newX][newY];
			area[newX][newY] = swapper;
			setPosition(newX, newY); //I don't have will to do that better
		}
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

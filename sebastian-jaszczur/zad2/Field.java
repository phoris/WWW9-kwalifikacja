
public class Field {
	public String toString() {
		return " -";
	}
	
	public int alive() {
		return 0;
	}
	
	public void interact(Surface surf, int turn, Field[][] area) {
		// nothing
	}
	
	public void doOrders(Surface surf, int turn, Field[][] area) {
		// nothing
	}
	
	public void setPosition(int x, int y) {
		// not necessary
	}
	
	public void setDirection(Direction dir) {
		// not necessary
	}
	
	public void getHit() {
		// nothing
	}
}

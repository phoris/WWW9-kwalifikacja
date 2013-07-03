
public class Field {
	boolean hitted = false;
	public String toString() {
		if(hitted) {
			hitted = false;
			return " #";
		}else
			return " -";
	}
	
	public int alive() {
		return 0;
	}
	
	public int interact(Surface surf, int turn, Field[][] area) {
		return 0; //zwraca ilosc "akcji" w tym ruchu
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
		hitted = true;
	}
}

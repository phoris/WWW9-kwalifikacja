
public class Bot extends Field {
	private char name;
	//private BotAI control;
	
	public Bot(char c) {
		name = c;
	}
	
	public void interact(Field[][] area) {
		
	}

	public String toString() {
		return "v"+name;
	}
}

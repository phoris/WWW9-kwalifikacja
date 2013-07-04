
public class Animacja {
	static int turnTime = 250; //to jest czas
	
	public static void main(String[] args) {
		Field[] bots = {new Bot('A', new ShotAI()), new Bot('B', new RandomAI())};
		Surface surface = new Surface(12, 8, bots);
		
		while(surface.alive()) {
			surface.run();
			System.out.println(surface);
			
			try { 
				Thread.sleep(turnTime);
			}catch ( InterruptedException e) { 
				System.out.println("This error should not happen");
			} 
		}
		Field winner = (bots[0].alive()>0?bots[0]:bots[1]);
		System.out.println("Wygral "+winner+" z hp="+winner.alive());
		System.out.println("Koniec");
	}

}

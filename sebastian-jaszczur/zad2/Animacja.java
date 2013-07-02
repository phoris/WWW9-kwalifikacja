
public class Animacja {

	
	public static void main(String[] args) {
		Field[] bots = {new Bot('A', new RandomAI()), new Bot('B', new RandomAI())};
		Surface surface = new Surface(12, 8, bots);
		
		while(surface.alive()) {
			surface.run();
			System.out.println(surface);
			
			try { 
				Thread.sleep(500);
			}catch ( InterruptedException e) { 
				System.out.println("This error should not happen");
			} 
		}
		
		System.out.println("Koniec");
	}

}

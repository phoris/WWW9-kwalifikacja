
public class Animacja {

	
	public static void main(String[] args) {
		Field[] bots = {new Bot('A'), new Bot('B')};
		Surface surface = new Surface(8, 8, bots);
		
		while(surface.alive()) {
			surface.run();
			System.out.println(surface);
			
			try { 
				Thread.sleep(100); 
			}catch ( InterruptedException e) { 
				System.out.println("This error should not happen");
			} 
		}
		
		System.out.println("Koniec");
	}

}

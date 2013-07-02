
public class RandomAI implements BotAI {

	@Override
	public Decision[] decide(int x, int y, Field[][] area, int actions) {
		Decision[] dec = new Decision[actions];
		for(int i=0; i<actions; i++) {
			dec[i] = Decision.randomDecision();
		}
		return dec;
	}

}

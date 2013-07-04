import java.util.Random;


public class ShotAI implements BotAI {
	private enum Action {
		SHOT, MOVE, TURN;
	}
	private Random randGen = new Random();
	private Action nextAction = Action.SHOT;
	@Override
	public Decision[] decide(int x, int y, Field[][] area, int actions) {
		Decision[] decisions = new Decision[actions];
		for(int i=0; i<actions; i++)
		{
			switch(nextAction) {
			case MOVE:
				decisions[i] = Decision.STEP;//(randGen.nextBoolean() ? Decision.STEP : Decision.BACKSTEP);
				nextAction = Action.TURN;
				break;
			case TURN:
				decisions[i] = (randGen.nextBoolean() ? Decision.LEFT : Decision.RIGHT);
				nextAction = Action.SHOT;
				break;
			case SHOT:
			default:
				decisions[i] = Decision.SHOT;
				nextAction = Action.MOVE;
				break;
			}
		}
		return decisions;
	}

}

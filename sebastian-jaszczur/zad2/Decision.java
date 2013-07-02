import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum Decision {
	SHOT, CHOP, STEP, BACKSTEP, LEFT, RIGHT;
	
	private static final List<Decision> VALUES =
			Collections.unmodifiableList(Arrays.asList(values()));
	 private static final int SIZE = VALUES.size();
	 private static final Random RANDOM = new Random();
	 public static Decision randomDecision()  {
		 return VALUES.get(RANDOM.nextInt(SIZE));
	 }
}

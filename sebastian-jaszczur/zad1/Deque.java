
public class Deque {
	private int realSize = 1;
	private int rightSize = 0;
	private int leftSize = 0;
	private int[] arr = new int[1];
	
	private void expandArray() {
		//not finished
	}
	
	private void shortenArray() {
		//not finished
	}
	
	void pushLeft(int el) {
		//not finished
		if(rightSize + leftSize < realSize) {
			
		}
	}
	
	void pushRight(int el) {
		//not finished
	}
	
	int popLeft() {
		//not finished
		return -1;
	}
	
	int popRight() {
		//not finished
		return -1;
	}
	
	int at(int pos) {
		//not finished
		return -1;
	}
	
	int size() {
		// FINISHED
		return rightSize+leftSize;
	}
	
	boolean isEmpty() {
		// FINISHED
		return (rightSize + leftSize > 0);
	}
}

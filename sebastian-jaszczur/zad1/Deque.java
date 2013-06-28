
public class Deque {
	private int realSize = 1;
	private int start = 0;
	private int userSize = 0;
	private int[] arr = new int[1];
	
	private int scaleIndex(int pos) {
		int val = start+pos;
		while(val<0)
			val += realSize;
		while(val>=realSize)
			val -= realSize; // ew. mozna zrobic modulo, ale w srednim przypadku -> to powinno byc lepsze
		return val;
	}
	
	private void changeArray(int newSize) {
		int[] newArr = new int[newSize];
		
		for(int i=0; i<userSize; i++) {
			newArr[i] = arr[scaleIndex(i)];
		}
		
		realSize = newSize;
		arr = newArr;
		start = 0;
	}
	
	private void expandArray() {
		changeArray(realSize*2);
	}
	
	private void shortenArray() {
		changeArray(realSize/2);
	}
	
	private void checkSize(int number) {
		if(userSize+number > realSize)
			expandArray();
		if((userSize+number)*4 <= realSize)
			shortenArray();
	}
	
	void pushLeft(int el) {
		checkSize(+1);
		start -= 1;
		userSize += 1;
		arr[scaleIndex(0)] = el;
	}
	
	void pushRight(int el) {
		checkSize(+1);
		userSize += 1;
		arr[scaleIndex(userSize-1)] = el;
	}
	
	int popLeft() {
		if(userSize <= 0) {
			return -1;
		}
		int val = arr[scaleIndex(0)];
		start += 1;
		userSize -= 1;
		checkSize(0);
		return val;
	}
	
	int popRight() {
		if(userSize <= 0) {
			return -1;
		}
		int val = arr[scaleIndex(userSize-1)];
		userSize -= 1;
		checkSize(0);
		return val;
	}
	
	int at(int pos) {
		if(pos < 0 || pos >= userSize) {
			return -1;
		}
		return arr[scaleIndex(pos)];
	}
	
	int size() {
		return userSize;
	}
	
	boolean isEmpty() {
		return userSize==0;
	}
	
	int hasCycle() {
		int one = 0;
		int two = 0;
		do {
			two = at(two);
			two = at(two);
			one = at(one);
		}while(one != two && two != -1);
		
		if(two == -1)
			return -1;
		
		int siz = 0;
		do {
			siz += 1;
			one = at(one);
		}while(one != two);
		return siz;
	}
}



public class TestDeque {
	static void printDeque(Deque d) {
		System.out.print("Deque: ");
		for(int i=0; i<d.size(); i++)
			System.out.print(d.at(i)+"; ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		Deque d = new Deque();
		System.out.println("Empty: "+d.isEmpty());
		printDeque(d);
		
		d.pushLeft(1);
		d.pushLeft(2);
		d.pushLeft(0);
		System.out.println("Empty: "+d.isEmpty());
		printDeque(d);
		
		d.pushRight(5);
		d.pushRight(3);
		d.pushRight(7);
		printDeque(d);
		
		System.out.println("popLeft= "+d.popLeft()+"; popRight= "+d.popRight()+";");
		printDeque(d);
		
		System.out.println("At: -2->"+d.at(-2)+"; 0->"+d.at(0)+"; 2->"+d.at(2)+"; 4->"+d.at(4));
		System.out.println("Empty: "+d.isEmpty());
		printDeque(d);
		
		for(int i=0; i<6; i++) {
			System.out.print("POP"+i+"->"+d.popRight()+"; ");
		}
		System.out.println();
		printDeque(d);
		System.out.println("Empty: "+d.isEmpty());
	}

}

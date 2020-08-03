
public class Bridge {

	public static void main(String[] args) {
		RandomQueue<Card> q = new RandomQueue<Card>();
		
		// Carrega todas as cartas do baralho
		for (int i = 0; i < 51; i++)
			q.enqueue(new Card(i));
	
		for (int i = 0; i < 13; i++) {
			StdOut.println(q.dequeue());
		}
		
	}

}

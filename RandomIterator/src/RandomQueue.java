import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {
	private Item[] q;
	private int N = 0;
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = q[i];
		}
		q = copy;
	}
	
	public RandomQueue() {
		q = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	public void enqueue(Item item) {
		if (N == q.length) resize(2 * q.length);
		q[N++] = item;
	}
	public Item dequeue() {
		if (N == 1) return q[--N];
		 
		int index = StdRandom.uniform(N-1);
		Item swap = q[index];
		q[index] = q[--N];
		q[N] = null;
		
		if (N > 0 && N == q.length/4) resize(q.length/2);
		
		return swap;
	}
	public Item sample() {
		int index = StdRandom.uniform(N-1);
		Item swap = q[index];
		q[index] = q[N-1];
		q[N-1] = swap;
		
		return swap;
	}
	
	public Iterator<Item> iterator() { return new RandomIterator(); }
	private class RandomIterator implements Iterator<Item> {
		
		private int i = N;
		
		public boolean hasNext() { return i > 0; }
		public void remove() { /* nao suportado */ }
		public Item next() {
			if (i == 1) return q[--i];
			
			int index = StdRandom.uniform(i-1);
			Item swap = q[--i];
			q[i] = q[index];
			q[index] = swap;
			return q[i];
		}
	}
}

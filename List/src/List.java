import java.util.Iterator;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;

public class List<Item> implements Iterable<Item> {
	private RedBlackBST<Double, Item> bst1;
	private LinearProbingHashST<Item, Double> bst2;
	private int N = 0;
	
	public void addFront(Item item) {
		Double first = bst1.select(0);
		Double media = first/2.0; // Media entre first e zero
		
		bst1.put(media, item);
		bst2.put(item, media);
		N++;
	}
	public void addBack(Item item) {
		N++;
		bst1.put((double)N, item);
		bst2.put(item, (double)N);
	}
	
	public Item deleteFront() {
		Double key = bst1.select(0);
		Item item = bst1.get(key);
		
		bst1.delete(key);
		bst2.delete(item);
		N--;
		
		return item;
	}
	public Item deleteBack() {
		Double key = bst1.select(N-1);
		Item item = bst1.get(key);
		
		bst1.delete(key);
		bst2.delete(item);
		N--;
		
		return item;
	}
	
	public void delete(Item item) {
		Double key = bst2.get(item);
		
		bst2.delete(item);
		bst1.delete(key);
		N--;
	}
	public void add(int i, Item item) {
		Double item1 = bst1.select(i);
		Double item2 = bst1.select(i-1);
		Double media = (item1+item2)/2.0; // Media entre first e zero
		
		bst1.put(media, item);
		bst2.put(item,media);
		N++;
	}
	
	public Item delete(int i) {
		Double key = bst1.select(i);
		Item item = bst1.get(key);
		
		bst1.delete(key);
		bst2.delete(item);
		N--;
		
		return item;
	}
	
	public Item get(int i) {
		Double key = bst1.select(i);
		return bst1.get(key);
	}
	
	public boolean contains(Item item) { return bst2.contains(item); }
	public boolean isEmpty() { return N==0; }
	public int size() { return N; }
	
	public List() { 
		bst1 = new RedBlackBST<Double, Item>(); 
		bst2 = new LinearProbingHashST<Item, Double>();
	}
	
	
	public Iterator<Item> iterator() {
		Queue<Item> queue = new Queue<Item>();
		for (Double key : bst1.keys())
			queue.enqueue(bst1.get(key));
		
		return queue.iterator();
	}
	
	public static void main(String[] args) {
		List<Integer> lista = new List<Integer>();

		lista.addBack(3);
		lista.addBack(4);
		lista.addFront(1);
		lista.addFront(0);
		lista.add(2, 2);
		
		StdOut.println("A lista deve imprimir a sequencia [0,1,2,3,4]: ");
		for (Integer v : lista)
			StdOut.println(v);
		StdOut.println("tamanho da lista deve ser 5: " + lista.size());
		
		StdOut.println("Deletando o segundo item: " + lista.delete(1));
		StdOut.println("Deletando o primeiro item: " + lista.deleteFront());
		StdOut.println("Deletando o ultimo item: " + lista.deleteBack());
		StdOut.println("A lista contem o numero 3? "+ lista.contains(3));
		StdOut.println("Deletando o numero 3...");
		lista.delete((Integer) 3); // Workaround para reconhecer inteiro como delete(Item item)
		
		StdOut.println("A lista deve imprimir o numero 2 apenas: ");
		for (Integer v : lista)
			StdOut.println(v);
		StdOut.println("tamanho da lista deve ser 1: " + lista.size());
		
		StdOut.println("Pega o primeiro item da lista: " + lista.get(0));
	}

	

}

/**
 * 
 * @author 6800149 (Rodrigo Alves Souza)
 *
 */

public class HubsAuthGenerator {

	public static void main(String[] args) {
		int N = StdIn.readInt();
		int M = StdIn.readInt();
		int H = StdIn.readInt();
		int A = StdIn.readInt();
		StdOut.println(N);
		
		// Gera N nós com M arestas randomicamente
		for (int i = 0; i < M; i++) {
			int n1 = (int)(Math.random() * N);
			int n2 = (int)(Math.random() * N);
			while (n1 == n2)
				n2 = (int)(Math.random() * N);
			StdOut.printf("%d %d\n", n1, n2);
		}
		
		// Para cada hub, seleciona randomicamente 10% das paginas e aponta para ele
		int hub, authority, page, pages;
		for (int i = 0; i < H; i++) {
			hub = i + N; // Próximo número possível para um Hub
			pages = (int)Math.round(N*0.1);
			for (int j = 0; j < pages; j++) {
				page = (int)(Math.random() * N);
				StdOut.printf(" %d %d ", page, hub);
			}
		}
		StdOut.print("\n");
		
		// Para cada authority, ele aponta randomicamente para 10% das paginas
		for (int i = 0; i < A; i++) {
			authority = i + N + H; // Próximo número possível para um Authority
			pages = (int)Math.round(N*0.1);
			for (int j = 0; j < pages; j++) {
				page = (int)(Math.random() * N);
				StdOut.printf(" %d %d ", authority, page);
			}
		}
	}

}

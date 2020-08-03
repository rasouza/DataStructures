import java.util.*;

public class TesteVisual {
	
	// Cria uma permutacao de 1 a N e devolve como string
	private static String permuta(int N) {
		RandomQueue<Integer> q = new RandomQueue<Integer>();
		String seq = "";
		// Cria a sequencia de N elementos
		for (int n = 1; n <= N; n++)
			q.enqueue(n);
		
		// Cria uma permutacao
		for(int n : q)
			seq += n;
			
		return seq;
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		ArrayList<String> permutacoes = new ArrayList<String>(); // Todas as permutacoes geradas
		Set<String> colecao = new HashSet<String>(); // Itens unicos
		
		// Cria as permutacoes T vezes
		for (int t = 0; t < T; t++) {
			String permutacao = "";
			permutacao = permuta(N);
			permutacoes.add(permutacao);
			colecao.add(permutacao);
		}
		
		Histogram hist = new Histogram(colecao.size());
		
		int index = 0;
		for (String permut : colecao) {
			int freq = Collections.frequency(permutacoes, permut);
			for (int i = 0; i < freq; i++) {
				hist.addDataPoint(index);
			}
			index++;
		}
		hist.draw();
	}

	
}

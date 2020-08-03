/**
 *  author: Rodrigo Alves Souza (6800149)
 */

// import edu.cs.princeton.algs4.*;

public class Percolation3D {

	public static boolean[][][] random(int N, double p) {
		boolean[][][] a = new boolean[N][N][N];

		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++) 
				for (int j = 0; j < N; j++) 
					a[i][j][k] = StdRandom.bernoulli(p); 

		return a;
	}

	// Inicializador do flow
	public static boolean[][][] flow(boolean[][][] open) {
		int N = open.length;
		boolean[][][] full = new boolean[N][N][N];

		// Comeca o flow colocando liquido na superficiedo cubo
		for (int k = 0; k < N; k++)
			for (int j = 0; j < N; j++)
				if (open[0][j][k]) {
					flow(open, full, 0, j, k);
				}

		return full;
	}

	// Recursividade do flow
	public static void flow(boolean[][][] open, boolean[][][] full, int i, int j, int k) {
		int N = full.length;

		// Se chegou na borda retorna
		if (i < 0 || i >= N || j < 0 || j >= N || k < 0 || k >= N)
			return;

		// Se esta fechado retorna
		if (!open[i][j][k])
			return;

		// Se ja esta cheio retorna
		if (full[i][j][k])
			return;

		full[i][j][k] = true;


		flow(open, full, i+1, j, k); // Baixo
		flow(open, full, i-1, j, k); // Cima
		flow(open, full, i, j+1, k); // Direita
		flow(open, full, i, j-1, k); // Esquerda
		flow(open, full, i, j, k+1); // Atras
		flow(open, full, i, j, k-1); // Frente
	}

	public static boolean percolates(boolean[][][] open) {
		int N = open.length;
		boolean[][][] full = flow(open);

		for (int j = 0; j < N; j++) 
			for (int k = 0; k < N; k++)
				if (full[N-1][j][k]) return true;

		return false;
		
	}

	public static void main(String[] args) {
		// Para usar com a entrada padrao

		boolean[][][] open = BooleanMatrix3D.readBoolean3D();
		BooleanMatrix3D.print(flow(open));
		StdOut.print(percolates(open));
	}
}
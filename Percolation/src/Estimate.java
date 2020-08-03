/**
 *  author: Rodrigo Alves Souza (6800149)
 */

// import edu.cs.princeton.algs4.*;

public class Estimate {

	public static double eval(int N, double p, int T) {
		int count = 0;
		for (int t = 0; t < T; t++) {
			boolean[][][] open = Percolation3D.random(N, p);
			// BooleanMatrix3D.print(open);
			if (Percolation3D.percolates(open)) count++;
		}

		return (double) count/T;
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		double p = Double.parseDouble(args[1]);
		int T = Integer.parseInt(args[2]);
		StdOut.printf("%f", eval(N, p, T));
	}
	
}
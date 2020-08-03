/**
 *  author: Rodrigo Alves Souza (6800149)
 */

// import edu.cs.princeton.algs4.*;

public class ProbCritica3D {
	public static double getCritical(int N, int T) {
		double error = 0.01; // Erro ajustavel para melhor desempenho
		double crit = 0.5;
		
		double a = 0.0;
		double b = 1.0;
		double c = (a+b)/2;
		double f = Estimate.eval(N, c, T);

		while (Math.abs(f - crit) > error) {
			if (f < crit)
				a = c;
			else
				b = c;
			
			c = (a+b)/2;
			f = Estimate.eval(N, c, T);
		} 
		
		return c;
	}
	
	public static void main(String[] args) {
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		StdOut.println();
		double crit = getCritical(N, T);
		
		StdOut.println(crit);
	}

}

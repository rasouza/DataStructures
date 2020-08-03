/**
 *  author: Rodrigo Alves Souza (6800149)
 */

// import edu.cs.princeton.algs4.*;

public class BooleanMatrix3D {

	public static boolean[][][] readBoolean3D() {
		int N = StdIn.readInt();
		int M = StdIn.readInt();
		int O = StdIn.readInt();

		boolean[][][] a = new boolean[N][M][O];

		for (int k = 0; k < O; k++)
			for (int i = 0; i < N; i++) 
				for (int j = 0; j < M; j++) 
					if (StdIn.readInt() != 0) a[i][j][k] = true; 

		return a;
	}

	public static void print(boolean[][][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				for (int k = 0; k < a[i][j].length; k++) {
 					if (a[j][k][i]) StdOut.print("1 ");
 					else StdOut.print("0 ");
 				}
 				StdOut.println();
 			}
			StdOut.println();
 		}
	}
}
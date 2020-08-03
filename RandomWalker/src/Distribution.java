import java.awt.Color;

public class Distribution {
	
	public static int visitas[][];
	
	// Funcao para normalizar a intensidade da cor
	public static int getMaxVisits() {
		int max = 1000;
		for (int i = 0; i < visitas.length; i++)
		    for (int j = 0; j < visitas.length; j++)
		        if (visitas[i][j] > max)
		        	max = visitas[i][j];
		return max;
	}
	
	public static void desenha(int[][] visitas, int L) {
		double norm = 255.0/getMaxVisits();
		
		StdDraw.setCanvasSize(600, 600);
		StdDraw.setScale(0,(2*L));
		
		for (int i = 0; i < 2*L; i++) {
			for (int j = 0; j < 2*L; j++) {
				int color = (int)Math.round(norm*visitas[i][j]);
				StdDraw.setPenColor(new Color(color, 0, 0));
				StdDraw.filledSquare(i, j, 1);
			}
		}
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);
		int L = Integer.parseInt(args[2]);
		
		visitas = new int[2*L][2*L];
		
		for (int m = 0; m < M; m++) {
			RandomWalker p = new RandomWalker();
			visitas[p.getX()][p.getY()]++;
			
			for (int n = 0; n < N; n++) {
				p.step(L);
				visitas[p.getX()][p.getY()]++;
			}
		}		
		
		desenha(visitas, L);
		
	}

}

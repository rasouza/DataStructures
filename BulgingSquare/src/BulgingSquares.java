

/**
 * Esse programa recebe uma matriz que monta o tabuleiro e outra
 * matriz que recebe a posicao em que os quadrados pequenos devem
 * ser pintados de acordo com seu tipo (1, 2, 3, 4, 5 ou 6)
 * 
 * @author 6800149 (Rodrigo Alves Souza)
 *
 */

public class BulgingSquares {
	
	private static int[][] grid; // Tabuleiro de quadrados intercalados
	private static int[][] tinyS; // Posicao dos quadrados pequenos na matriz
	private static final double lado = 0.5; // Tamanho do quadrado do tabuleiro
	private static final double ladoPequeno = lado/4.0; // Tamanho dos quadrados menores
	
	/**
	 * Seleciona a cor da caneta que 
	 * os quadrados deverao ser pintados
	 * 
	 * @param x posicao horizontal do grid
	 * @param y posicao vertical do grid
	 * @param pequeno booleano que ve se voce esta
	 * pintando tabuleiro ou quadrados menores
	 */
	public static void setPenColor(int x, int y, boolean pequeno) {
		if ((grid[x][y] == 1 && pequeno == false) || (grid[x][y] == 0 && pequeno == true))
			StdDraw.setPenColor(StdDraw.BLACK);
		else
			StdDraw.setPenColor(StdDraw.WHITE);
	}
	
	public static void main(String[] args) {
		grid = StdArrayIO.readInt2D();
		tinyS = StdArrayIO.readInt2D();
		
		StdDraw.setXscale(0,15);
		StdDraw.setYscale(-15,0);
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				
				setPenColor(x,y, false);
				
				// Desenha todo o grid primeiramente
				if (grid[x][y] == 1)
					StdDraw.filledSquare(y+0.5, -x-0.5, lado);
				else
					StdDraw.filledSquare(y+0.5, -x-0.5, lado);
			
				// Prepara caneta para desenhar quadrados pequenos
				if (tinyS[x][y] != 0)
					setPenColor(x,y, true);
				
				switch (tinyS[x][y]) {
				// Desenha os quadrados pequenos no segundo e quarto quadrante do plano cartesiano
				case 1:
					StdDraw.filledSquare(y+0.8, -(x+0.2), ladoPequeno);
					StdDraw.filledSquare(y+0.2, -(x+0.8), ladoPequeno);
					break;
					
				// Desenha no eixo y positivo
				case 2:
					StdDraw.filledSquare(y+0.8, -(x+0.8), ladoPequeno);
					StdDraw.filledSquare(y+0.2, -(x+0.8), ladoPequeno);
					break;
				
				// Desenha o primeiro quadrante e o terceiro
				case 3:
					StdDraw.filledSquare(y+0.2, -(x+0.2), ladoPequeno);
					StdDraw.filledSquare(y+0.8, -(x+0.8), ladoPequeno);
					break;
					
				// Desenha o eixo x negativo
				case 4:
					StdDraw.filledSquare(y+0.8, -(x+0.2), ladoPequeno);
					StdDraw.filledSquare(y+0.8, -(x+0.8), ladoPequeno);
					break;
					
				// Desenha o eixo x positivo
				case 5:
					StdDraw.filledSquare(y+0.2, -(x+0.2), ladoPequeno);
					StdDraw.filledSquare(y+0.2, -(x+0.8), ladoPequeno);
					break;
					
				// Desenha o eixo y negativo
				case 6:
					StdDraw.filledSquare(y+0.8, -(x+0.2), ladoPequeno);
					StdDraw.filledSquare(y+0.2, -(x+0.2), ladoPequeno);
					break;
				
				}
			}
		}
	}	
}

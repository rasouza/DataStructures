
public class RandomWalker {
	
	private int x = 0;
	private int y = 0;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void step(int L) {
		L = 2*L; // Converte para o tamanho total do plano
		int direcao = StdRandom.uniform(4);
		
		switch (direcao) {
		
		// Direita
		case 0:
			// Não deixa passar do tamanho total do plano
			if (x == L-1)
				step(L/2);
			else
				x++;
			break;
			
		// Esquerda
		case 1:
			// Não deixa passar do tamanho total do plano
			if (x == 0)
				step(L/2);
			else
				x--;
			break;
			
		// Cima
		case 2:
			// Não deixa passar do tamanho total do plano
			if (y == L-1)
				step(L/2);
			else
				y++;
			break;
			
		// Baixo
		case 3:
			// Não deixa passar do tamanho total do plano
			if (y == 0)
				step(L/2);
			else
				y--;
			break;
		}
	}
	
	public double distance() {
		return Math.sqrt(x*x + y*y);
	}

}

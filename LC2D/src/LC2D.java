import edu.princeton.cs.algs4.Bag;

public class LC2D {
	private static DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF();
	
	private static boolean connected(Bag<Point2D>[][] grid, double d) {
		int G = (int) (Math.ceil(1.0 / d));
		
		// Grid com 1 ponto apenas deve retornar false
		if (uf.size()-1 <= 1)
			return false;
		
		// Varre todos os grids que contem pontos
		for(int i = 0; i < G+2; i++)
			for(int j = 0; j < G+2; j++)
				if (grid[i][j].size() > 0) 
					
					// Varre todos os pontos do grid
					for (Point2D p : grid[i][j])
						
					// Procura por pontos conexos nos possiveis grids
						for (int row = i-1; row <= i+1; row++)
							for (int col = j-1; col <= j+1; col++)
								
								// Se algum ponto dos possiveis grids for conexo, adiciona no UnionFind
								for (Point2D q : grid[row][col])
									if (p.distanceTo(q) <= d && p.id() != q.id())
										uf.union(p.id(), q.id());
		
		// Verifica se esta todo mundo conectado
		int componente = uf.find(1);
		for (int i = 1; i < uf.size(); i++) {
			if (uf.find(i) != componente)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		double d = Double.parseDouble(args[0]);
		int T = Integer.parseInt(args[1]);
		int S = Integer.parseInt(args[2]);
				
		int G = (int) (Math.ceil(1.0 / d));
		
		int id, count = 0;
		double media = 0, var = 0;
		
		
		// Seta o Seed
		StdRandom.setSeed(S);
		
		for (int t = 0; t < T; t++) {
			// Cria o grid
			@SuppressWarnings("unchecked")
			Bag<Point2D>[][] grid = (Bag<Point2D>[][]) new Bag[G+2][G+2];
			for (int i = 0; i < G+2; i++)
	            for (int j = 0; j < G+2; j++)
	                grid[i][j] = new Bag<Point2D>();
			
			// Gera os pontos aleatoriamente
			while(!connected(grid, d)) {
				id = uf.newSite() - 1;
				double x = StdRandom.uniform();
				double y = StdRandom.uniform();
				Point2D p = new Point2D(x,y,id);
				
				// Adiciona no grid
				int row = 1+(int)(y*G);
				int col = 1+(int)(x*G);
				grid[row][col].add(p);
//				StdOut.printf("Ponto (%f,%f) adicionado no grid (%d,%d) \n", x, y, row, col);
			}
			
			// Contagem de pontos do experimento atual
			for(int i = 0; i < G+2; i++)
				for(int j = 0; j < G+2; j++)
					count += grid[i][j].size();
			media += (double)count/T;
			var += Math.pow(media - count, 2);
		}
		
		StdOut.println(media);
		StdOut.println(var);
		
	}

}

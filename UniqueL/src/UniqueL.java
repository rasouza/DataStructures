import java.util.Random;

public class UniqueL {
	private static TST<String> trieST = new TST<String>();
//	private static RedBlackBST<String, String> trieST = new RedBlackBST<String, String>();
//	private static SeparateChainingHashST<String, String> trieST = new SeparateChainingHashST<String, String>();
//	private static DigitTrieST<String> trieST = new DigitTrieST<String>();
	
	// popula a 10-way Trie de acordo com o comprimento L
	private static void populateST(String text, ITabelaSimbolo<String, String> trieST, int L) {
		int len = text.length();
		for(int i = 0; i < len - L; i++) {
			String substr = text.substring(i, i+L);
			if (!trieST.contains(substr))
				trieST.put(substr, substr);
		}
	}
	
	// Verifica se a sequencia eh L-completa
	private static boolean checkLCompleta(ITabelaSimbolo<String, String> trieST, int L) {
		Double max = Math.pow(10, L);
		
		for (int i = 0; i < max.intValue(); i++) {
			String seq = String.format("%0"+L+"d", i);
			if (!trieST.contains(seq))
				return false;
		}
		
		return true;
	}
	
	// Caso L = 0
	private static void longestL() {
		int L = 0;
		
		// no pior caso: 10^7 para ler 10million-pi
		char[] chars = new char[10000000];
		for(int i = 0; i < 10000000; i++) {
			if (!StdIn.hasNextChar())
				break;
			chars[i] = StdIn.readChar();
		}
		
		
		do {
			L++;
			populateST((new String(chars)).trim(), trieST, L);
		} while(checkLCompleta(trieST, L));
		
		StdOut.println(L-1);
	}
	
	// Caso L = 0 e segundo argumento N presente
	private static void longestRandomL(int N) {
		int L = 0;
	
		// Gera a sequencia aleatoria
		Random random = new Random();
		char[] chars = new char[N];
		for (int i = 0; i < N; i++)
			chars[i] = (char)('0' + random.nextInt(10));
		
		// Verifica o maior L
		do {
			L++;
			populateST(new String(chars), trieST, L);
		} while(checkLCompleta(trieST, L));
		
		StdOut.println(L-1);
	}
	
	// Caso L positivo
	private static void uniqueStrings(int L) {
		String text = StdIn.readString();
		
		populateST(text, trieST, L);		
		
		StdOut.println(trieST.size());
	}

	public static void main(String[] args) {
		// Leitura dos parametros
		int L, N = 0;
		switch (args.length) {
			case 1:
				L = Integer.parseInt(args[0]);
				break;
				
			case 2:
				L = Integer.parseInt(args[0]);
				N = Integer.parseInt(args[1]);
				break;
				
			default:
				throw new IllegalArgumentException("Numero de parametros invalido");
		}
		
		final long startTime = System.currentTimeMillis();
		if (L > 0)
			uniqueStrings(L);
		else if (L == 0 && args.length == 1)
			longestL();
		else if (L == 0 && args.length == 2)
			longestRandomL(N);
		else
			throw new IllegalArgumentException("Parametros invalidos");
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime)/1000.0 );
		
		
//		RedBlackBST<String, String> redblackST = new RedBlackBST<String, String>();
//		SeparateChainingHashST<String, String> hashST = new SeparateChainingHashST<String, String>();
	}

}

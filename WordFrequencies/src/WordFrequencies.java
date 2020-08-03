/**
 *  author: Rodrigo Alves Souza (6800149)
 *  
 *  Resolucao do problema: a biblioteca Collections possui uma funcao sort
 *  onde voce pode definir o criterio de comparacao se voce prover um Comparator.
 *  No Comparator eh possivel definir uma funcao que retorna positivo ou negativo 
 *  entre dois valores para dizer qual eh maior que o outro e, automaticamente, 
 *  ele ordena para voce.
 */

// import edu.cs.princeton.algs4.*;

import java.util.*;

public class WordFrequencies {
	
	public static TreeMap<String, Integer> palavras = new TreeMap<String, Integer>();
	
	public static List<String> ordena() {
        List<String> lista = new ArrayList<String> (palavras.keySet());
        Collections.sort(lista, new Comparator<String>() {
        	public int compare (String w1, String w2) {
                return palavras.get(w2) - palavras.get(w1);
            }
        });
        return lista; 
    }

	public static void main(String[] args) {
		String texto = StdIn.readAll();
		
		// Conta todas as palavras do texto
	    for (String p : texto.split(" ")) {
	    	// Remove todas as pontuacoes e digitos
	    	String palavra = p.toLowerCase().replaceAll("(?!-)[\\p{Punct}\\d]", "");
	    	if (palavra.equals("")) continue;
	    	
	        Integer n = palavras.get(palavra);
	        n = (n == null)? 1 : ++n;
	        palavras.put(palavra, n);
	    }
	    
	    List<String> lista = ordena();
	    for (String w: lista)
	    	StdOut.printf("%s %d\n", w, palavras.get(w));
    }
}

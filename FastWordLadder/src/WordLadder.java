import java.util.*;

import edu.princeton.cs.algs4.ST;

/******************************************************************************
 *  Compilation:  javac WordLadder.java
 *  Execution:    java WordLadder word1 word2 < wordlist.txt
 *  Dependencies: Graph.java IndexSET.java In.java BreadthFirstPaths.java
 *
 *  Data files:   http://algs4.cs.princeton.edu/41graph/words5.txt
 *                http://algs4.cs.princeton.edu/41graph/words6.txt
 *                http://algs4.cs.princeton.edu/41graph/words5-knuth.txt
 *
 *  Creates a minimum length word ladder connecting two words.
 *
 *  java WordLadder words5.txt
 *  flirt break
 *  length = 11
 *  flirt
 *  flint
 *  fling
 *  cling
 *  clink
 *  click
 *  clock
 *  cloak
 *  croak
 *  creak
 *  break
 *
 *  allow brown
 *  NOT CONNECTED
 *
 *  white house
 *  length = 18
 *  white
 *  while
 *  whale
 *  shale
 *  shake
 *  slake
 *  slate
 *  plate
 *  place
 *  peace
 *  peach
 *  poach
 *  coach
 *  couch
 *  cough
 *  rough
 *  rouge
 *  rouse
 *  house
 *  
 *  % java WordLadder words5-knuth.txt
 *  white house
 *  length = 9
 *  white
 *  whits
 *  shits
 *  shots
 *  soots
 *  roots
 *  routs
 *  route
 *  rouse
 *  house
 *
 ******************************************************************************/

public class WordLadder {
	static Graph G;
	static List<String> words = new ArrayList<String>();
	static ST<String, Integer> st = new ST<String, Integer>();
    // return true if two strings differ in exactly one letter
    public static boolean isNeighbor(String a, String b) {
    	if (Math.abs(a.length() - b.length()) == 1) {
    		if (a.startsWith(b) || b.startsWith(a)) return true;
    		else return false;
    	}
    	else if (a.length() == b.length()) {
	        int differ = 0;
	        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
	            if (a.charAt(i) != b.charAt(i)) differ++;
	            if (differ > 1) return false;
	        }
	        return true;
    	}
    	else return false;
    }
    
    public static void addNeighbors(int index, List<String> lista) {
//    	int index = lista.indexOf(word);
//    	int pos = words.indexOf(word);
    	String word = lista.get(index);
    	int pos = st.get(word);
    	int size = lista.size();
  	   	for (int i = 1; (index + i) < size && isNeighbor(word, lista.get(index + i)); i++) {
  	   		String word2 = lista.get(index + i);
  	   		G.addEdge(pos, st.get(word2));
  	   	}
    }
    
    

    public static void main(String[] args) {

       /*******************************************************************
        *  Read a list of strings, all of the same length.
        *******************************************************************/
        In in = new In(args[0]);
        int maxlen = 0;
        int j = 0;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() > maxlen) maxlen = word.length();
            words.add(word);
            st.put(word, j++);
        }

       /*******************************************************************
        *  Insert connections between neighboring words into graph.
        *  This construction process can be improved from LN^2 in the worst
        *  case to L^2 N in the worst case by L radix sorts where
        *  N = number of strings and L = length of each words.
        *
        *  We avoid inserting two copies of each edge by checking if
        *  word1.compareTo(word2) < 0
        *
        *******************************************************************/
        G = new Graph(words.size());
        
        // Usado para dar sort
        List<String> b = new ArrayList<String>(words.size());
        b.addAll(words);

        // Ordena N vezes, tal que N eh o comprimento da maior palavra na lista
        for (int i = 0; i < maxlen; i++) {
        	b.sort(new CyclicalComparator(i));
        	for (String word : b) addNeighbors(st.get(word), b);
        }
        StdOut.println("Construiu o grafo");

       /*******************************************************************
        *  Run breadth first search
        *******************************************************************/
        while (!StdIn.isEmpty()) {
            String from = StdIn.readString();
            String to   = StdIn.readString();
            if (!words.contains(from)) throw new RuntimeException(from + " is not in word list");
            if (!words.contains(to))   throw new RuntimeException(to   + " is not in word list");

            BreadthFirstPaths bfs = new BreadthFirstPaths(G, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {
                StdOut.println(bfs.distTo(words.indexOf(to)));
                for (int v : bfs.pathTo(words.indexOf(to))) {
                    StdOut.println(words.get(v));
                }
            }
            else StdOut.println("NOT CONNECTED");
            StdOut.println();
        }
    }
}

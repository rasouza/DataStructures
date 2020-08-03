import edu.princeton.cs.algs4.Queue;

/******************************************************************************
 *  Compilation:  javac TrieST.java
 *  Execution:    java TrieST < words.txt
 *  Dependencies: StdIn.java
 *
 *  Symbol table with string keys, implemented using a ternary search
 *  trie (TrieST).
 *
 *
 *  % java TrieST < shellsST.txt
 *  keys(""):
 *  by 4
 *  sea 6
 *  sells 1
 *  she 0
 *  shells 3
 *  shore 7
 *  the 5
 *
 *  longestPrefixOf("shellsort"):
 *  shells
 *
 *  keysWithPrefix("shor"):
 *  shore
 *
 *  keysThatMatch(".he.l."):
 *  shells
 *
 *  % java TrieST
 *  theory the now is the time for all good men
 *
 *  Remarks
 *  --------
 *    - can't use a key that is the empty string ""
 *
 ******************************************************************************/

/**
 *  The <tt>TrieST</tt> class represents an symbol table of key-value
 *  pairs, with string keys and generic values.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides character-based methods for finding the string
 *  in the symbol table that is the <em>longest prefix</em> of a given prefix,
 *  finding all strings in the symbol table that <em>start with</em> a given prefix,
 *  and finding all strings in the symbol table that <em>match</em> a given pattern.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be <tt>null</tt>&mdash;setting the
 *  value associated with a key to <tt>null</tt> is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a ternary search trie.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/52trie">Section 5.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class TrieST<Value> {
    private int N;              // size
    private Node<Value> root;   // root of TrieST

    private static class Node<Value> {
        private char c;                        // character
        private Node<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
        private int palavras = 0;			   // numero de palavras embaixo de cada no
        private int rank;					   // rank da palavra na trie
    }

    /**
     * Initializes an empty string symbol table.
     */
    public TrieST() {
    }
    
    
    public Value floor(String key) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<Value> x = floor(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

	private Node<Value> floor(Node<Value> x, String key, int d) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        if (x == null) return null;
        
        // Já se posicionou, vai tudo para a direita e para baixo (lexicograficamente maior de todos os menores)
        if (d < 0) {
        	if (x.right != null) 
        		return floor(x.right, key, -1);
        	else if (x.mid != null) 
        		return floor(x.mid, key, -1);
        	else return x;
        	
        }
        
        char c = key.charAt(d);
        if      (c < x.c)
        	return floor(x.left,  key, d);
        else if (c == x.c && d < key.length() - 1)
        	return floor(x.mid, key, d+1);
        else 
        	return floor(x.mid, key, -1); 
//        else return x;
    }
	
	public Value ceil(String key) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<Value> x = ceil(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

	private Node<Value> ceil(Node<Value> x, String key, int d) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        if (x == null) return null;
        
        // Já se posicionou, pega o primeiro da trie (lexicograficamente menor de todos os maiores)
        if (d < 0) {
        	if (x.val != null) return x;
        	else if (x.left != null) 
        		return ceil(x.left, key, -1);
        	else if (x.mid != null)
        		return ceil(x.mid, key, -1);
        	else 
        		return x;
        }
        
        if (d < 0) {
        	return x;
        }
        
        char c = key.charAt(d);
        if      (c > x.c)              return ceil(x.right,  key, d);
        else if (c == x.c && d < key.length() - 1) {
        	if (x.mid != null)
        		return ceil(x.mid, key, d+1);
        	else {
        		char next_c = key.charAt(d+1);
        		if (next_c > x.c && x.right != null)
        			return ceil(x.right, key, -1);
        		else
        			return x;
        	}
        		
        }
        else return ceil(x.mid, key, -1); 
//        else return x;
    }
	
	public int rank(String key) {
		if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<Value> x = rank(root, key, 0, 0);
        if (x == null) throw new RuntimeException("a palavra nao existe na trie");
        return x.rank;
	}
	
	// Contador de nodes anteriores ao node argumento para retornar o rank
	private int countNodes(Node<Value> node) {
		int acc = 0;
		Node<Value> nodeCounter = node;
    	while (nodeCounter.left != null) {
    		nodeCounter = nodeCounter.left;
    		acc += nodeCounter.palavras;
    	}
    	
    	return acc;
	}
	
	private Node<Value> rank(Node<Value> x, String key, int d, int acc) {
		if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        if (x == null) return null;
        
        
        char c = key.charAt(d);
        if      (c < x.c) {
//        	acc += countNodes(x.left);
        	return rank(x.left,  key, d, acc);
        }
        else if (c > x.c) {
        	x.right.left = x;
//        	acc += countNodes(x.right);
        	return rank(x.right, key, d, acc);
        }
        else if (d < key.length() - 1) {
        	if (x.val != null) acc++;
        	acc += countNodes(x);
        	return rank(x.mid,   key, d+1, acc);
        }
        else {
        	x.rank = acc;
        	return x;
        }
	}
	public Value select(int rank) {
		if (rank < 0) throw new IllegalArgumentException("rank must be positive and not null");
                
        // Vai para a primeira letra
        Node<Value> first = root;
        while (first.left != null) {
        	Node<Value> temp = first;
        	first = first.left;
        	first.right = temp;
        }
        Node<Value> x = select(first, rank, 0);
        if (x == null) return null;
        return x.val;
              
	}
	
	private Node<Value> select(Node<Value> x, int rank, int acc) {
		// Base da recursao
		if (acc == rank && x.val != null)
			return x;
		
		acc += x.palavras;
		
		if (acc > rank) {
			// Vai para a primeira letra
	        Node<Value> first = x.mid;
	        while (first.left != null){
	        	Node<Value> temp = first;
	        	first = first.left;
	        	first.right = temp;
	        }
	        
	        // Continua a busca sem aumentar o acumulador
			return select(first, rank, acc - x.palavras);
        }
        else {
        	// Passa para a proxima letra
        	return select(x.right, rank, acc);
        }
	}
    
    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return N;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *     <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(String key) {
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and <tt>null</tt> if the key is not in the symbol table
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Value get(String key) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    // return subtrie corresponding to given key
    private Node<Value> get(Node<Value> x, String key, int d) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        if (x == null) return null;
        char c = key.charAt(d);
        if      (c < x.c)              return get(x.left,  key, d);
        else if (c > x.c)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is <tt>null</tt>, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(String key, Value val) {
        if (!contains(key)) N++;
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
            
        }
        x.palavras++;
        if      (c < x.c) {
        	x.palavras--;
        	x.left  = put(x.left,  key, val, d);
        }
        else if (c > x.c) {
        	x.palavras--;
        	x.right = put(x.right, key, val, d);
        }
        else if (d < key.length() - 1) {
        	x.mid   = put(x.mid,   key, val, d+1);
        }
        else                            x.val   = val;
        
        
        return x;
    }

    /**
     * Returns the string in the symbol table that is the longest prefix of <tt>query</tt>,
     * or <tt>null</tt>, if no such string.
     * @param query the query string
     * @return the string in the symbol table that is the longest prefix of <tt>query</tt>,
     *     or <tt>null</tt> if no such string
     * @throws NullPointerException if <tt>query</tt> is <tt>null</tt>
     */
    public String longestPrefixOf(String query) {
        if (query == null || query.length() == 0) return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if      (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.val != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    /**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     * @return all keys in the sybol table as an <tt>Iterable</tt>
     */
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    /**
     * Returns all of the keys in the set that start with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the keys in the set that start with <tt>prefix</tt>,
     *     as an iterable
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }


    /**
     * Returns all of the keys in the symbol table that match <tt>pattern</tt>,
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the symbol table that match <tt>pattern</tt>,
     *     as an iterable, where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }
 
    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, queue);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) queue.enqueue(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i+1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, queue);
    }


    /**
     * Unit tests the <tt>TrieST</tt> data type.
     */
    public static void main(String[] args) {

        // build symbol table from standard input
        TrieST<Integer> st = new TrieST<Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }
        
        StdOut.println("st.ceil(\"bateram\"): " + st.ceil("bateram"));
        StdOut.println("st.floor(\"car\"): " + st.floor("car"));
        StdOut.println("st.floor(\"a\"): " + st.floor("a"));
        StdOut.println("st.ceil(\"zzzzzz\"): " + st.ceil("zzzzzz"));
        StdOut.println("st.select(3): " + st.select(3));
        StdOut.println("st.rank(\"shore\"): " + st.rank("shore"));
        

//        StdOut.println("longestPrefixOf(\"shellsort\"):");
//        StdOut.println(st.longestPrefixOf("shellsort"));
//        StdOut.println();
//
//        StdOut.println("longestPrefixOf(\"shell\"):");
//        StdOut.println(st.longestPrefixOf("shell"));
//        StdOut.println();
//
//        StdOut.println("keysWithPrefix(\"shor\"):");
//        for (String s : st.keysWithPrefix("shor"))
//            StdOut.println(s);
//        StdOut.println();
//
//        StdOut.println("keysThatMatch(\".he.l.\"):");
//        for (String s : st.keysThatMatch(".he.l."))
//            StdOut.println(s);
    }
}

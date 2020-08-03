import edu.princeton.cs.algs4.Stack;

/******************************************************************************
 *  Compilation:  javac DepthFirstDirectedPaths.java
 *  Execution:    java DepthFirstDirectedPaths G s
 *  Dependencies: Digraph.java Stack.java
 *
 *  Determine reachability in a digraph from a given vertex using
 *  depth first search.
 *  Runs in O(E + V) time.
 *
 *  % tinyDG.txt 3
 *  3 to 0:  3-5-4-2-0
 *  3 to 1:  3-5-4-2-0-1
 *  3 to 2:  3-5-4-2
 *  3 to 3:  3
 *  3 to 4:  3-5-4
 *  3 to 5:  3-5
 *  3 to 6:  not connected
 *  3 to 7:  not connected
 *  3 to 8:  not connected
 *  3 to 9:  not connected
 *  3 to 10:  not connected
 *  3 to 11:  not connected
 *  3 to 12:  not connected
 *
 ******************************************************************************/

/**
 *  The <tt>DepthFirstDirectedPaths</tt> class represents a data type for finding
 *  directed paths from a source vertex <em>s</em> to every
 *  other vertex in the digraph.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 *  For additional documentation,  
 *  see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of  
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DepthFirstDirectedPaths {
    private boolean[] marked;  // marked[v] = true if v is reachable from s
    private int[] edgeTo;      // edgeTo[v] = last edge on path from s to v
    private final int s;       // source vertex
    private Digraph Gnew;
    /**
     * Computes a directed path from <tt>s</tt> to every other vertex in digraph <tt>G</tt>.
     * @param G the digraph
     * @param s the source vertex
     */
    public DepthFirstDirectedPaths(Digraph G, int s) {
    	Gnew = new Digraph(G.V());
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }
    
    public Digraph getOneWayStreet() {
    	return Gnew;
    }

    private void dfs(Digraph G, int v) { 
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
            	Gnew.addEdge(v, w);
                edgeTo[w] = v;
                dfs(G, w);
            }
            else 
            	if (edgeTo[v] != w)
            		Gnew.addEdge(w, v);
            
        }
    }

    /**
     * Is there a directed path from the source vertex <tt>s</tt> to vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a directed path from the source
     *   vertex <tt>s</tt> to vertex <tt>v</tt>, <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    
    /**
     * Returns a directed path from the source vertex <tt>s</tt> to vertex <tt>v</tt>, or
     * <tt>null</tt> if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a directed path from the source vertex
     *   <tt>s</tt> to vertex <tt>v</tt>, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}

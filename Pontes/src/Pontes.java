import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Queue;

public class Pontes {

	public static void main(String[] args) {
		int V = StdIn.readInt();
		int E = StdIn.readInt();
		Digraph G = new Digraph(V);
		
		for(int i = 0; i < E; i++) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			if (v >= V || w >= V) throw new RuntimeException("Vértice fora do range permitido");
			G.addEdge(v, w);
			G.addEdge(w, v);
		}

		DepthFirstDirectedPaths paths = new DepthFirstDirectedPaths(G, 0);
		Digraph oneWay = paths.getOneWayStreet();
		KosarajuSharirSCC scc = new KosarajuSharirSCC(oneWay);

        // number of connected components
        int M = scc.count();

        // compute list of vertices in each strong component
        @SuppressWarnings("unchecked")
		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) components[i] = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)	components[scc.id(v)].enqueue(v);
        
        // printa as pontes
        for (DirectedEdge e : oneWay.edges())
        	if(scc.id(e.from()) != scc.id(e.to())) 
        		StdOut.println(e.from() + " " + e.to());
        
	}

}


public class DynamicWeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	private int N = 0;
	
	private void resize(int capacity) {
		int[] copy = new int[capacity];
		int[] sz_copy = new int[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = id[i];
			sz_copy[i] = sz[i];
		}
		id = copy;
		sz = sz_copy;
	}
	
	public DynamicWeightedQuickUnionUF() {
		N = 1;
		id = new int[1];
		sz = new int[1];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 0;
		}
	}
	
	public int find(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		id[i] = j;
		
		// Weigthed Quick Union
		if (sz[i] < sz[j])	{ id[i] = j; sz[j] += sz[i]; }
		else				{ id[j] = i; sz[i] += sz[j]; }
	}
	
	public int newSite() {
		if (N == id.length) resize(2 * N);
		id[N] = N;
		sz[N] = 0;
		return ++N;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF();
		int par1, par2, id = 0, j;
		
		while(!StdIn.isEmpty()){
			par1 = StdIn.readInt();
			par2 = StdIn.readInt();
			
			id = uf.newSite();
			uf.union(par1, par2);
		}
		
		for (int i = 0; i < id; i++) {
			j = uf.find(i);
			if (i != j) {
				StdOut.print(i);
				StdOut.println(j);
			}
		}
	}

}

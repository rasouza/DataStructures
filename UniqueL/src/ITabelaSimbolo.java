
public interface ITabelaSimbolo<Key, Value> {
	// Apenas os metodos necessarios para o exercicio
	public void put(Key key, Value value);
	public Value get(Key key);
	public int size();
	public boolean contains(Key key);
}

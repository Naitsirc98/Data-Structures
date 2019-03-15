package datastructures.lists;

public interface List<T> extends AbstractList<T> {

	void insert(int index, T value);
	T replaceAt(int index, T value);
	void replace(T old, T repl);
	void swap(int i, int j);
	void addFirst(T value);
	void addLast(T value);
	
}

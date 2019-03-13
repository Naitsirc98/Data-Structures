package datastructures.linear;

public interface List<T> extends AbstractList<T> {

	int lastIndexOf(T value);
	int count(T value);
	void insert(int index, T value);
	T replaceAt(int index, T value);
	void replace(T old, T repl);
	void swap(int i, int j);
	void addFirst(T value);
	void addLast(T value);
	
}

package datastructures;

public interface AbstractCollection<T> extends Iterable<T> {
	
	int size();
	boolean isEmpty();
	void clear();
	boolean contains(T value);
	T[] toArray();
	
	default boolean containsAll(Iterable<T> other) {
		for(T t : other) {
			if(!contains(t))
				return false;
		}
		return true;
	}
	
}

package datastructures;

public interface Collection<T> extends AbstractCollection<T> {
	
	boolean add(T value);
	boolean remove(T value);

	default boolean addAll(Iterable<T> other) {
		
		for(T t : other) 
			add(t);
		
		return true;
	}


	default boolean removeAll(Iterable<T> other) {
		boolean removeAll = true;
		
		for(T t : other) {
			removeAll &= remove(t);
		}
		
		return removeAll;
	}

	
	@SuppressWarnings("unchecked")
	default boolean addAll(T... values) {
		for(T t : values) 
			add(t);
		
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	default boolean removeAll(T... values) {
		boolean removeAll = true;
		
		for(T t : values) {
			removeAll &= remove(t);
		}
		
		return removeAll;
	}


}

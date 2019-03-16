package datastructures.sets;

import datastructures.lists.ArrayList;

public class ArraySet<T> extends SetListWrapper<T> {

	public ArraySet() {
		super(new ArrayList<>());
	}

	public ArraySet(Iterable<T> other) {
		super(new ArrayList<>(), other);
	}

	@SafeVarargs
	public ArraySet(T... values) {
		super(new ArrayList<>(), values);
	}
	
	

}

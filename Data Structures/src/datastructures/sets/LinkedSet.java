package datastructures.sets;

import datastructures.lists.LinkedList;

public class LinkedSet<T> extends SetListWrapper<T> {

	public LinkedSet() {
		super(new LinkedList<>());
	}

	public LinkedSet(Iterable<T> other) {
		super(new LinkedList<>(), other);
	}

	@SafeVarargs
	public LinkedSet(T... values) {
		super(new LinkedList<>(), values);
	}
	

}

package datastructures.sets;

import datastructures.lists.DoublyLinkedList;

public class DoublyLinkedSet<T> extends SetListWrapper<T> {

	public DoublyLinkedSet() {
		super(new DoublyLinkedList<>());
	}

	public DoublyLinkedSet(Iterable<T> other) {
		super(new DoublyLinkedList<>(), other);
	}

	@SafeVarargs
	public DoublyLinkedSet(T... values) {
		super(new DoublyLinkedList<>(), values);
	}
	
	
}

package datastructures.lists.sorted;

import java.util.Comparator;

import datastructures.lists.LinkedList;

public class SortedLinkedList<T> extends SortedListWrapper<T> {

	public SortedLinkedList() {
		super(new LinkedList<>());
	}
	
	public SortedLinkedList(Comparator<T> comparator) {
		super(new LinkedList<>(), comparator);
	}
	
	@SafeVarargs
	public SortedLinkedList(T...values) {
		super(new LinkedList<>(), values);
	}

	public SortedLinkedList(Iterable<T> other) {
		super(new LinkedList<>(), other);
	}
	
	public SortedLinkedList(Iterable<T> other, Comparator<T> comparator) {
		super(new LinkedList<>(), other, comparator);
	}

}

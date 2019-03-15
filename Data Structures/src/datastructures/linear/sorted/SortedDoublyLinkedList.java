package datastructures.linear.sorted;

import java.util.Comparator;

import datastructures.linear.DoublyLinkedList;

public class SortedDoublyLinkedList<T> extends SortedListWrapper<T> {

	public SortedDoublyLinkedList() {
		super(new DoublyLinkedList<>());
	}
	
	public SortedDoublyLinkedList(Comparator<T> comparator) {
		super(new DoublyLinkedList<>(), comparator);
	}
	
	@SafeVarargs
	public SortedDoublyLinkedList(T...values) {
		super(new DoublyLinkedList<>(), values);
	}

	public SortedDoublyLinkedList(Iterable<T> other) {
		super(new DoublyLinkedList<>(), other);
	}
	
	public SortedDoublyLinkedList(Iterable<T> other, Comparator<T> comparator) {
		super(new DoublyLinkedList<>(), other, comparator);
	}

}
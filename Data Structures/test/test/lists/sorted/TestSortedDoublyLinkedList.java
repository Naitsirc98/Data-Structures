package test.lists.sorted;

import datastructures.lists.AbstractList;
import datastructures.lists.sorted.SortedDoublyLinkedList;

public class TestSortedDoublyLinkedList extends TestSortedList {

	@Override
	protected AbstractList<Integer> getList() {
		return new SortedDoublyLinkedList<>();
	}

}

package test.lists.sorted;

import datastructures.lists.AbstractList;
import datastructures.lists.sorted.SortedLinkedList;

public class TestSortedLinkedList extends TestSortedList {

	@Override
	protected AbstractList<Integer> getList() {
		return new SortedLinkedList<>();
	}

	
}

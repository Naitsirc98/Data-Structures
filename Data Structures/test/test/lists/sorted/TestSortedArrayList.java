package test.lists.sorted;

import datastructures.lists.AbstractList;
import datastructures.lists.sorted.SortedArrayList;

public class TestSortedArrayList extends TestSortedList {

	@Override
	protected AbstractList<Integer> getList() {
		return new SortedArrayList<>();
	}

}

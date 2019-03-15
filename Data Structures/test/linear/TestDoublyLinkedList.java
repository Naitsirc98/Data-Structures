package linear;

import datastructures.lists.DoublyLinkedList;
import datastructures.lists.List;

public class TestDoublyLinkedList extends TestList {

	@Override
	protected List<Integer> getList() {
		return new DoublyLinkedList<>();
	}

}

package linear;

import datastructures.linear.DoublyLinkedList;
import datastructures.linear.List;

public class TestDoublyLinkedList extends TestList {

	@Override
	protected List<Integer> getList() {
		return new DoublyLinkedList<>();
	}

}

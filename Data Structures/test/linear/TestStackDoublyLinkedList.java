package linear;

import datastructures.lists.DoublyLinkedList;
import datastructures.restrictive.Stack;

public class TestStackDoublyLinkedList extends TestStack {

	@Override
	protected Stack<Integer> getStack() {
		return new DoublyLinkedList<>();
	}


}

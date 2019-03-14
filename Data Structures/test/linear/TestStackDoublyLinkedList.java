package linear;

import datastructures.linear.DoublyLinkedList;
import datastructures.linear.Stack;

public class TestStackDoublyLinkedList extends TestStack {

	@Override
	protected Stack<Integer> getStack() {
		return new DoublyLinkedList<>();
	}


}

package linear;

import datastructures.lists.DoublyLinkedList;
import datastructures.restrictive.Queue;

public class TestQueueDoublyLinkedList extends TestQueue {

	@Override
	protected Queue<Integer> getQueue() {
		return new DoublyLinkedList<>();
	}


}

package linear;

import datastructures.linear.DoublyLinkedList;
import datastructures.linear.Queue;

public class TestQueueDoublyLinkedList extends TestQueue {

	@Override
	protected Queue<Integer> getQueue() {
		return new DoublyLinkedList<>();
	}


}

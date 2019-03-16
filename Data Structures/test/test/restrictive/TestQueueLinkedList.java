package test.restrictive;

import datastructures.lists.LinkedList;
import datastructures.restrictive.Queue;

public class TestQueueLinkedList extends TestQueue {

	@Override
	protected Queue<Integer> getQueue() {
		return new LinkedList<>();
	}


}

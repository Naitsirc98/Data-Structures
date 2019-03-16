package test.restrictive;

import datastructures.lists.ArrayList;
import datastructures.restrictive.Queue;

public class TestQueueArrayList extends TestQueue {

	@Override
	protected Queue<Integer> getQueue() {
		return new ArrayList<>();
	}


}

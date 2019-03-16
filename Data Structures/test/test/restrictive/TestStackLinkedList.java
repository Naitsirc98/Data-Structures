package test.restrictive;

import datastructures.lists.LinkedList;
import datastructures.restrictive.Stack;

public class TestStackLinkedList extends TestStack {

	@Override
	protected Stack<Integer> getStack() {
		return new LinkedList<>();
	}

	

}

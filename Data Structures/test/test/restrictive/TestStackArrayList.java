package test.restrictive;

import datastructures.lists.ArrayList;
import datastructures.restrictive.Stack;

public class TestStackArrayList extends TestStack {

	@Override
	protected Stack<Integer> getStack() {
		return new ArrayList<>();
	}

}

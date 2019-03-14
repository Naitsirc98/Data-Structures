package linear;

import datastructures.linear.ArrayList;
import datastructures.linear.Stack;

public class TestStackArrayList extends TestStack {

	@Override
	protected Stack<Integer> getStack() {
		return new ArrayList<>();
	}

}

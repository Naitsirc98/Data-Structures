package test.sets;

import datastructures.sets.AbstractSet;
import datastructures.sets.DoublyLinkedSet;

public class TestDoublyLinkedSet extends TestSet {

	@Override
	protected AbstractSet<Integer> getSet() {
		return new DoublyLinkedSet<>();
	}

}

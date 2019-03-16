package test.sets;

import datastructures.sets.AbstractSet;
import datastructures.sets.LinkedSet;

public class TestLinkedSet extends TestSet {

	@Override
	protected AbstractSet<Integer> getSet() {
		return new LinkedSet<>();
	}


}

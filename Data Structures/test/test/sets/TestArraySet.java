package test.sets;

import datastructures.sets.AbstractSet;
import datastructures.sets.ArraySet;

public class TestArraySet extends TestSet {

	@Override
	protected AbstractSet<Integer> getSet() {
		return new ArraySet<>();
	}


}

package test.sets.sorted;

import org.junit.Before;
import org.junit.Test;

import datastructures.sets.sorted.SortedSet;
import datastructures.util.ErrorChecks;
import test.sets.TestAbstractSet;

public abstract class TestSortedSet extends TestAbstractSet {
	
	@Before
	public void init() {
		this.set = getSet();
		ErrorChecks.assertThat(set instanceof SortedSet, ClassCastException.class);
	}
	
	@Test
	public void testDefaultOrdering() {
		
	}

}

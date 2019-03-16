package test.sets;

import org.junit.Before;
import org.junit.Test;

import datastructures.sets.AbstractSet;

public abstract class TestAbstractSet {
	
	protected AbstractSet<Integer> set;

	protected abstract AbstractSet<Integer> getSet();
	
	@Before
	public void init() {
		set = getSet();
	}
	
	@Test
	public abstract void testUnion();
	@Test
	public abstract void testIntersection();
	@Test
	public abstract void testComplement();
	
}

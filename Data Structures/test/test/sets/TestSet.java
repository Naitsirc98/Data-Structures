package test.sets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import datastructures.lists.AbstractList;
import datastructures.lists.ArrayList;
import datastructures.sets.AbstractSet;
import datastructures.sets.Set;
import datastructures.util.ErrorChecks;

public abstract class TestSet extends TestAbstractSet {

	private static final Integer[] data = new Integer[] {
			1,2,3,4,5,
			2,3,4,5,1,
			6,7,8,9,10,
			7,8,6,10,9,
			11,12,13,14,
			15,16,17,18,
			19,5,6,16,20
	};

	private static final Integer[] dataNoRepetition = new Integer[] {
		1,2,3,4,5,
		6,7,8,9,10,
		11,12,13,14,
		15,16,17,18,
		19,20	
	};
	
	protected void addData() {
		Set<Integer> set = (Set<Integer>) this.set;
		set.addAll(data);
	}
	
	@Override
	@Before
	public void init() {
		set = getSet();
		ErrorChecks.assertThat(set instanceof Set, ClassCastException.class);
	}
	
	@Override
	@Test
	public void testUnion() {
		
		assertEquals(getSet(), set.union(set));
		
		addData();
		
		assertEquals(set, set.union(set));
		
		Set<Integer> set = (Set<Integer>) this.set.copy();
		
		set.add(-1);
		set.add(-2);
		set.add(-3);
		
		AbstractSet<Integer> result = this.set.union(set);
	
		assertEquals(set, result);
		
	}

	@Override
	@Test
	public void testIntersection() {
		
		assertEquals(getSet(), set.intersection(set));
		
		addData();
		
		assertEquals(set, set.intersection(set));
		
		Set<Integer> set = (Set<Integer>) this.set.copy();
		
		set.add(-1);
		set.add(-2);
		set.add(-3);
		
		AbstractSet<Integer> result = this.set.intersection(set);
	
		assertEquals(this.set, result);
		
	}

	@Override
	@Test
	public void testComplement() {
		
		assertEquals(getSet(), set.complement(set));
		
		addData();
		
		assertEquals(getSet(), set.complement(set));
		
		Set<Integer> set = (Set<Integer>) this.set.copy();
		
		set.clear();
		
		set.add(-1);
		set.add(-2);
		set.add(-3);
		
		AbstractSet<Integer> result = this.set.complement(set);
	
		assertEquals(this.set, result);
	}
	
	@Test
	public void clear() {
	
		set.clear();
		
		addData();
		
		set.clear();
		
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void contains() {
		
		assertFalse(set.contains(0));
		
		addData();
		
		for(int i = 0;i < data.length;i++) {
			assertTrue(set.contains(data[i]));
		}
		
	}

	@Test
	public void add() {
		
		assertTrue(set.isEmpty());
		
		addData();
		
		assertFalse(set.isEmpty());
		
		AbstractList<Integer> list = new ArrayList<>(set);
		
		// Check repetition
		for(Integer i : list) {
			assertEquals(1, list.count(i));
		}
		
	}

	@Test
	public void remove() {
		
		Set<Integer> set = (Set<Integer>) this.set;
		
		assertFalse(set.remove(1));
		
		addData();
		
		for(int i = 0;i < dataNoRepetition.length;i++) {
			final int size = set.size();
			assertTrue(set.remove(dataNoRepetition[i]));
			assertEquals(size-1, set.size());
		}
		
	}
	
}

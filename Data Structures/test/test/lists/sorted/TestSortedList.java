package test.lists.sorted;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import datastructures.lists.AbstractList;
import datastructures.lists.sorted.SortedList;
import datastructures.util.ErrorChecks;
import test.lists.TestAbstractList;

public abstract class TestSortedList extends TestAbstractList {

	@Override
	@Before
	public void init() {
		list = (SortedList<Integer>) getList();
		ErrorChecks.assertThat(list instanceof SortedList, ClassCastException.class);
	}
	
	@Override
	@Test
	public void testFirst() {
		
		for(int i = 20;i >= 0;i--) {
			list.add(i);
		}
		
		assertEquals(0, list.first().intValue());
		
	}
	
	@Override
	@Test
	public void testLast() {
		addData();
		assertEquals(data.length-2, list.last().intValue());
	}
	
	@Override
	@Test
	public void testIndexOf() {
		
		assertTrue(list.indexOf(null) < 0);
		assertTrue(list.indexOf(0) < 0);
		
		for(int i = 20;i >= 0;i--) {
			list.add(i);
		}
		
		assertEquals(5, list.indexOf(5));

		assertTrue(list.indexOf(-1) < 0);
		
	}
	
	@Test
	public void testDefaultOrdering() {
		
		Random rand = new Random();
		
		for(int i = 0;i < 50;i++) {
			list.add(rand.nextInt(100));
		}
		
		for(int i = 1;i < 50;i++) {
			assertTrue(list.get(i-1) <= list.get(i));
		}
		
	}
	
	@Test
	public void testReverseOrdering() {
		
		((SortedList<Integer>)list).setComparator(Comparator.reverseOrder());
		
		Random rand = new Random();
		
		for(int i = 0;i < 50;i++) {
			list.add(rand.nextInt(100));
		}
		
		// Repetition
		list.add(list.get(rand.nextInt(list.size())));
		list.add(list.get(rand.nextInt(list.size())));
		
		for(int i = 1;i < list.size();i++) {
			assertTrue(list.get(i-1) >= list.get(i));
		}
		
	}
	
	@Test
	public void testSetComparator() {
		addData();
		
		((SortedList<Integer>)list).setComparator(Comparator.reverseOrder());
		
		for(int i = 1;i < list.size();i++) {
			assertTrue(list.get(i-1) >= list.get(i));
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testExceptionNullSetComparator() {
		((SortedList<Integer>)list).setComparator(null);
	}
	
	@Test
	public void testHigher() {
		
		SortedList<Integer> list = (SortedList<Integer>) super.list;
		
		addData();
		
		assertEquals(getList(), list.higher(list.last()));
		
		Integer min = list.get(data.length / 2);
		
		AbstractList<Integer> result = list.higher(min);
		
		for(int i = 1;i < result.size();i++) {
			assertTrue(result.get(i-1) > min);
		}
	}
	
	@Test
	public void testLower() {
		
		SortedList<Integer> list = (SortedList<Integer>) super.list;
		
		addData();
		
		assertEquals(getList(), list.lower(list.first()));
		
		Integer max = list.get(data.length / 2);
		
		AbstractList<Integer> result = list.lower(max);
		
		for(int i = 1;i < result.size();i++) {
			assertTrue(result.get(i-1) < max);
		}
	}
	
}

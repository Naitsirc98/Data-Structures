package test.lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import datastructures.lists.AbstractList;

public abstract class TestAbstractList {

	protected AbstractList<Integer> list;
	protected static Integer[] data;
	
	static {
		data = new Integer[100];
		for(int i = 0;i < data.length-1;i++) {
			data[i] = i;
		}
		data[data.length-1] = 0;
	}
	
	protected void addData() {
		
		for(Integer i : data) {
			list.add(i);
		}
	}
	
	protected abstract AbstractList<Integer> getList();
	
	@Before
	public void init() {
		list = getList();
	}

	@Test
	public void testClear() {
		list.clear();
		assertTrue(list.isEmpty());
		addData();
		list.clear();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testContains() {
		
		assertFalse(list.contains(null));
		assertFalse(list.contains(0));
		
		addData();
		
		for(int i = 0;i < data.length;i++) {
			assertTrue(list.contains(data[i]));
		}
		
		assertFalse(list.contains(-1));
	}
	
	@Test
	public void testContainsAll() {
		addData();
		assertTrue(list.containsAll(Arrays.asList(data)));
	}
	
	@Test
	public void testAdd() {
		
		for(int i = 0;i < data.length;i++) {
			assertTrue(list.add(data[i]));
			assertTrue(list.contains(data[i]));
		}
		
		assertEquals(data.length, list.size());
		
		// Repetition
		assertTrue(list.add(10));
	}
	
	@Test
	public void testRemove() {
		assertFalse(list.remove(null));
		assertFalse(list.remove(0));
		
		for(int i = 0;i < data.length-1;i++) {
			list.add(data[i]);
		}
		
		for(int i = 0;i < data.length-1;i++) {
			final int oldSize = list.size();
			assertTrue("While removing "+data[i], list.remove(data[i]));
			assertFalse("While removing "+data[i], list.contains(data[i]));
			assertTrue("While removing "+data[i], oldSize > list.size());
		}
		
		assertTrue(list.isEmpty());
	}

	@Test
	public void testAddAll() {
		
		Iterable<Integer> other = Arrays.asList(data);
		
		assertTrue(list.addAll(other));
		assertEquals(data.length, list.size());
		assertTrue(list.containsAll(other));
		
	}
	
	@Test
	public void testRemoveAll() {
		
		assertFalse(list.removeAll(Arrays.asList(data)));
		
		addData();
		
		assertTrue(list.removeAll(Arrays.asList(data)));
		
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testIndexOf() {
		
		assertTrue(list.indexOf(null) < 0);
		assertTrue(list.indexOf(0) < 0);
		
		for(int i = 0;i < 20;i++) {
			list.add(200);
		}
		
		assertEquals(0, list.indexOf(200));

		assertTrue(list.indexOf(-1) < 0);
		
	}
	
	@Test
	public void testFirst() {
		list.add(7);
		assertEquals(7, list.first().intValue());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testExceptionFirst() {
		list.first();
	}
	
	@Test
	public void testLast() {
		addData();
		assertEquals(0, list.last().intValue());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testExceptionLast() {
		list.last();
	}
	
	@Test
	public void testGet() {
		
		list.add(8);
		
		assertEquals(8, list.get(0).intValue());
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionGet() {
		list.get(0);
	}
	
	@Test
	public void testRemoveAt() {
		
		list.add(1);
		assertEquals(1, list.removeAt(0).intValue());
		
		for(int i = 0;i < 10;i++) {
			list.add(i);
		}
		
		final int[] indices = {3,5,3,3,0,4,3,1,1,0};
		
		final int size = list.size();
		
		for(int i = 0;i < size;i++) {
			final int oldSize = list.size();
			final int index = indices[i];
			final int value = list.get(index);
			final int removed = list.removeAt(index);
			assertEquals(value, removed);
			assertTrue(oldSize > list.size());
		}
		
		assertTrue(list.isEmpty());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionRemoveAt() {
		list.removeAt(0);
	}
	
	@Test
	public void testSublist() {
		
		addData();
		
		assertEquals(getList(), list.sublist(0, 0));
		assertEquals(list, list.sublist(0, list.size()));
	}
	
	@Test(expected = AssertionError.class)
	public void testExceptionSublist() {
		addData();
		list.sublist(10, 9);
	}
	
	@Test
	public void testLastIndexOf() {

		for(int i = 0;i < 10;i++) {
			list.add(800);
		}
		
		assertEquals(list.size()-1, list.lastIndexOf(800));
		
	}
	
	@Test
	public void testCount() {
		
		assertEquals(0, list.count(null));
		assertEquals(0, list.count(4));
		
		addData();
		
		assertEquals(0, list.count(null));
		assertEquals(1, list.count(4));
		
		for(int i = 0;i < 5;i++) {
			list.add(-1);
		}
		
		assertEquals(5, list.count(-1));
		
	}
	
	
	
	

}

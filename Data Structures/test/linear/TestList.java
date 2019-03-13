package linear;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import datastructures.linear.AbstractList;
import datastructures.linear.ArrayList;
import datastructures.linear.List;

public abstract class TestList {

	protected List<Integer> list;
	protected static Integer[] data;
	
	static {
		data = new Integer[100];
		for(int i = 0;i < data.length-1;i++) {
			data[i] = i;
		}
		data[data.length-1] = null;
		
		System.out.println(Arrays.toString(data));
	}
	
	protected void addData() {
		
		for(Integer i : data) {
			list.add(i);
		}
	}
	
	protected abstract List<Integer> getList();
	
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
		assertTrue(list.add(null));
		assertTrue(list.add(10));
	}
	
	@Test
	public void testRemove() {
		assertFalse(list.remove(null));
		assertFalse(list.remove(0));
		
		addData();
		
		for(int i = 0;i < data.length;i++) {
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
		
		addData();
		
		list.addFirst(1);
		assertEquals(0, list.indexOf(1));
		
		list.addLast(1);
		assertEquals(0, list.indexOf(1));
		
		assertTrue(list.indexOf(-1) < 0);
	}
	
	public void testFirst() {
		
		addData();
		
		assertEquals(data[0], list.first());
		
		list.remove(data[0]);
		
		assertEquals(data[1], list.first());
	}
	
	@Test(expected = AssertionError.class)
	public void testExceptionFirst() {
		list.first();
	}
	
	@Test
	public void testLast() {
		addData();
		
		list.addLast(null);
		assertNull(list.last());
		
		list.addLast(94);
		assertEquals(94, list.last().intValue());
		
	}
	
	@Test(expected = AssertionError.class)
	public void testExceptionLast() {
		list.last();
	}
	
	@Test
	public void testGet() {
		
		addData();

		list.addFirst(5);
		assertEquals(5, list.get(0).intValue());
		
		list.addLast(5);
		assertEquals(5, list.get(list.size()-1).intValue());
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionGet() {
		list.get(0);
	}
	
	@Test
	public void testRemoveAt() {
		
		addData();
		
		Random rand = new Random();
		
		for(int i = 0;i < data.length;i++) {
			final int oldSize = list.size();
			final int index = rand.nextInt(oldSize);
			final Integer value = list.get(index);
			assertEquals(value, list.removeAt(index));
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
		
		addData();
		
		list.addFirst(-1);

		assertEquals(list.indexOf(-1), list.lastIndexOf(-1));
		
		list.addLast(-1);
		
		assertEquals(list.size()-1, list.lastIndexOf(-1));
		
	}
	
	@Test
	public void testCount() {
		
		assertEquals(0, list.count(null));
		assertEquals(0, list.count(4));
		
		addData();
		
		assertEquals(1, list.count(null));
		assertEquals(1, list.count(4));
		
		for(int i = 0;i < 5;i++) {
			list.add(-1);
		}
		
		assertEquals(5, list.count(-1));
		
	}
	
	@Test
	public void testInsert() {
		
		for(int i = 0;i < 10;i++) {
			list.add(i);
		}
		
		int oldSize = list.size();
		
		list.insert(list.size() / 2, null);
		
		assertTrue(oldSize < list.size());
		
		oldSize = list.size();
		list.insert(0, -1);
		assertEquals(-1, list.first().intValue());
		assertTrue(oldSize < list.size());
		
		oldSize = list.size();
		list.insert(list.size(), -1);
		assertEquals(-1, list.last().intValue());
		assertTrue(oldSize < list.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionInsert() {
		list.insert(1, null);
	}
	
	@Test
	public void testReplace() {
		
		addData();
		
		int index = list.indexOf(null);
		list.replace(null, 1);
		assertEquals(1, list.get(index).intValue());
		assertEquals(0, list.count(null));
		assertFalse(list.contains(null));
		
		
		index = list.indexOf(8);
		list.replace(8, 1);
		assertEquals(1, list.get(index).intValue());
		assertEquals(0, list.count(8));
		assertFalse(list.contains(8));
	}
	
	@Test
	public void testReplaceAt() {
		
		addData();
		
		Integer first = list.first();
		
		assertEquals(first, list.replaceAt(0, list.last()));
		assertEquals(list.last(), list.first());
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionReplaceAt() {
		list.replaceAt(0, 3);
	}
	
	@Test
	public void testSwap() {
		
		addData();
		
		Integer first = list.first();
		Integer last = list.last();
		
		list.swap(0, list.size()-1);
		
		assertEquals(first, list.last());
		assertEquals(last, list.first());
		
		Integer i = list.get(5);
		list.swap(5, 5);
		
		assertEquals(i, list.get(5));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionSwap() {
		list.swap(3, 5);
	}
	
	@Test
	public void testAddFirst() {
		
		list.addFirst(9);
		assertEquals(9, list.first().intValue());
		
		addData();
		
		list.addFirst(4);
		assertEquals(4, list.first().intValue());
	}
	
	@Test
	public void testAddLast() {
		
		list.addLast(9);
		assertEquals(9, list.last().intValue());
		
		addData();
		
		list.addLast(4);
		assertEquals(4, list.last().intValue());
	}
	

}











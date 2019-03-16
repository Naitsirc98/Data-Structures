package test.lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import datastructures.lists.List;
import datastructures.util.ErrorChecks;

public abstract class TestList extends TestAbstractList {
	
	@Override
	@Before
	public void init() {
		list = getList();
		ErrorChecks.assertThat(list instanceof List, ClassCastException.class);
	}

	@Test
	public void testInsert() {
		
		List<Integer> list = (List<Integer>) super.list; 
		
		for(int i = 0;i < 10;i++) {
			list.add(i);
		}
		
		int oldSize = list.size();
		
		list.insert(list.size() / 2, 89);
		
		assertTrue(oldSize < list.size());
		

		oldSize = list.size();
		list.insert(0, -1);
		assertEquals(-1, list.first().intValue());
		assertTrue(oldSize < list.size());

		oldSize = list.size();
		list.insert(list.size()-1, 100);
		assertEquals(100, list.get(list.size()-2).intValue());
		assertTrue(oldSize < list.size());
		
		oldSize = list.size();
		list.insert(list.size(), -1);
		assertEquals(-1, list.last().intValue());
		assertTrue(oldSize < list.size());
		
		list.clear();
		
		assertTrue(list.isEmpty());
		
		for(int i = 0;i < 10;i++) {
			list.add(i);
		}
		
		int[] values = new int[11];
		
		Iterator<Integer> it = list.iterator();
		
		for(int i = 0;it.hasNext();i++) {
			if(i == 1)
				values[1] = -1;
			else
				values[i] = it.next();
		}
		
		list.insert(1, -1);
		assertEquals(11, list.size());
		
		for(int i = 0;i < values.length;i++) {
			assertEquals(values[i], list.get(i).intValue());
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionInsert() {
		List<Integer> list = (List<Integer>) super.list; 
		list.insert(1, null);
	}
	
	@Test
	public void testReplace() {
		List<Integer> list = (List<Integer>) super.list; 
		addData();
		
		int index = list.indexOf(10);
		list.replace(10, 1);
		assertEquals(1, list.get(index).intValue());
		assertEquals(0, list.count(10));
		assertFalse(list.contains(10));
		
		
		index = list.indexOf(8);
		list.replace(8, 1);
		assertEquals(1, list.get(index).intValue());
		assertEquals(0, list.count(8));
		assertFalse(list.contains(8));
	}
	
	@Test
	public void testReplaceAt() {
		List<Integer> list = (List<Integer>) super.list; 
		addData();
		
		Integer first = list.first();
		
		assertEquals(first, list.replaceAt(0, list.last()));
		assertEquals(list.last(), list.first());
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionReplaceAt() {
		List<Integer> list = (List<Integer>) super.list; 
		list.replaceAt(0, 3);
	}
	
	@Test
	public void testSwap() {
		List<Integer> list = (List<Integer>) super.list; 
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
		List<Integer> list = (List<Integer>) super.list; 
		list.swap(3, 5);
	}
	
	@Test
	public void testAddFirst() {
		List<Integer> list = (List<Integer>) super.list; 
		list.addFirst(9);
		assertEquals(9, list.first().intValue());
		
		addData();
		
		list.addFirst(4);
		assertEquals(4, list.first().intValue());
	}
	
	@Test
	public void testAddLast() {
		List<Integer> list = (List<Integer>) super.list; 
		list.addLast(9);
		assertEquals(9, list.last().intValue());
		
		addData();
		
		list.addLast(4);
		assertEquals(4, list.last().intValue());
	}
	

}












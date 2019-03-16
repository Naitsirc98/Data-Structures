package test.restrictive;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import datastructures.restrictive.Stack;

public abstract class TestStack {

	private Integer[] data = {1,2,3,4,5};
	
	private Stack<Integer> stack;
	
	private void addData() {
		for(Integer i : data) {
			stack.push(i);
		}
	}
	
	protected abstract Stack<Integer> getStack();
	
	@Before
	public void init() {
		stack = getStack();
	}
	
	@Test
	public void testPush() {
		
		assertTrue(stack.isEmpty());
		stack.push(1);
		assertEquals(1, stack.size());
		addData();
		assertEquals(data.length+1, stack.size());
		
	}
	
	@Test
	public void testPop() {

		stack.push(1);
		assertEquals(1, stack.pop().intValue());
		assertEquals(0, stack.size());
		stack.push(0);
		assertEquals(0, stack.pop().intValue());
		assertEquals(0, stack.size());
		
		addData();
		
		for(int i = data.length-1;!stack.isEmpty();i--) {
			assertEquals(data[i], stack.pop());
		}
		
		assertTrue(stack.isEmpty());
	}
	
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testExceptionPop() {
		stack.pop();
	}
	
	@Test
	public void testPeek() {
		stack.push(1);
		assertEquals(1, stack.peek().intValue());
		assertEquals(1, stack.size());
		stack.push(0);
		assertEquals(0, stack.peek().intValue());
		assertEquals(2, stack.size());
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void testExceptionPeek() {
		stack.peek();
	}
	
	@Test
	public void testClear() {
		stack.clear();
		addData();
		stack.clear();
		assertTrue(stack.isEmpty());
	}

}

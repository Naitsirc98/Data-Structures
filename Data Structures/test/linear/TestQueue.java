package linear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import datastructures.linear.Queue;

public abstract class TestQueue {
private Integer[] data = {1,2,3,4,5};
	
	private Queue<Integer> queue;
	
	private void addData() {
		for(Integer i : data) {
			queue.enqueue(i);
		}
	}
	
	protected abstract Queue<Integer> getQueue();
	
	@Before
	public void init() {
		queue = getQueue();
	}
	
	@Test
	public void testEnqueue() {
		
		assertTrue(queue.isEmpty());
		queue.enqueue(1);
		assertEquals(1, queue.size());
		addData();
		assertEquals(data.length+1, queue.size());
		
	}
	
	@Test
	public void testPoll() {
		queue.enqueue(1);
		assertEquals(1, queue.poll().intValue());
		assertEquals(0, queue.size());
		queue.enqueue(0);
		assertEquals(0, queue.poll().intValue());
		assertEquals(0, queue.size());
		
		addData();
		
		for(int i = 0;!queue.isEmpty();i++) {
			assertEquals(data[i], queue.poll());
		}
		
		assertTrue(queue.isEmpty());
	}
	
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testExceptionPop() {
		queue.poll();
	}
	
	@Test
	public void testFirst() {
		queue.enqueue(1);
		assertEquals(1, queue.first().intValue());
		assertEquals(1, queue.size());
		queue.enqueue(0);
		assertEquals(1, queue.first().intValue());
		assertEquals(2, queue.size());
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void testExceptionFirst() {
		queue.first();
	}
	
	@Test
	public void testLast() {
		queue.enqueue(1);
		assertEquals(1, queue.last().intValue());
		assertEquals(1, queue.size());
		queue.enqueue(0);
		assertEquals(0, queue.last().intValue());
		assertEquals(2, queue.size());
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void testExceptionLast() {
		queue.last();
	}
	
	@Test
	public void testClear() {
		queue.clear();
		addData();
		queue.clear();
		assertTrue(queue.isEmpty());
	}


}

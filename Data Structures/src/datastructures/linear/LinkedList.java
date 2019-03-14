package datastructures.linear;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.util.ErrorChecks;

public class LinkedList<T> implements List<T> {
	
	private class Node {
		
		private Node next;
		private T value;
		
		Node(T value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.valueOf("value: "+value);
		}
		
	}
	
	private Node head;
	private int size;
	private int serial = Integer.MIN_VALUE;
	
	public LinkedList() {
		
	}
	
	@SafeVarargs
	public LinkedList(T...values) {
		addAll(values);
	}
	
	public LinkedList(Iterable<T> other) {
		addAll(other);
	}
	
	private int indexOfNull() {
		
		Node node = head;
		
		for(int i = 0;i < size;i++) {
			if(node.value == null)
				return i;
			
			node = node.next;
		}
		
		return -1;
	}

	@Override
	public int indexOf(T value) {
		
		if(value == null)
			return indexOfNull();
		
		Node node = head;
		
		for(int i = 0;i < size;i++) {
			
			if(value.equals(node.value))
				return i;
			
			node = node.next;
		}
		
		return -1;
	}
	
	private int lastIndexOfNull() {
		Node node = head;
		int index = -1;
		
		for(int i = 0;i < size;i++) {
			if(node.value == null) {
				index = i;
			}
			
			node = node.next;
		}
		
		return index;
	}
	
	@Override
	public int lastIndexOf(T value) {
		
		if(value == null) 
			return lastIndexOfNull();
		
		Node node = head;
		int index = -1;
		
		for(int i = 0;i < size;i++) {
			if(value.equals(node.value)) {
				index = i;
			}
			
			node = node.next;
		}
		
		return index;
	}

	@Override
	public T first() {
		ErrorChecks.assertThat(size > 0, NoSuchElementException.class);
		return head.value;
	}

	@Override
	public T last() {
		ErrorChecks.assertThat(size > 0, NoSuchElementException.class);
		
		Node node = head;
		
		while(node.next != null) {
			node = node.next;
		}
		
		return node.value;
	}

	@Override
	public T get(int index) {
		ErrorChecks.indexCheck(index, 0, size);
		
		Node node = head;
		
		for(int i = 0;i < index;i++) {
			node = node.next;
		}
		
		return node.value;
	}

	@Override
	public T removeAt(int index) {
		ErrorChecks.indexCheck(index, 0, size);
		
		T value;
		
		if(index == 0) {
			value = head.value;
			head = head.next;
		} else {
			Node node = head;
			
			for(int i = 0;i < index-1;i++) {
				node = node.next;
			}
			
			value = node.next.value;
			node.next = node.next.next;
		}
		

		size--;
		serial++;
		
		return value;
	}

	@Override
	public AbstractList<T> sublist(int min, int max) {
		ErrorChecks.assertThat(min <= max, "min > max");
		ErrorChecks.assertThat(min >= 0, "min < 0");
		ErrorChecks.assertThat(max <= size, "max > size");
		ErrorChecks.assertThat(size > 0, "List is empty");
		
		List<T> result = new LinkedList<>();
		
		Node node = head;
		
		for(int i = 0;i < min;i++) {
			node = node.next;
		}
		
		for(int i = min;i < max;i++) {
			result.addLast(node.value);
			node = node.next;
		}
		
		return result;
	}

	@Override
	public boolean add(T value) {
		
		if(size == 0) {
			head = new Node(value);
		} else {
			
			final Node tmp = head;
			head = new Node(value);
			head.next = tmp;
			
		}
		
		size++;
		serial++;
		
		return true;
	}
	
	private boolean removeNull() {
		
		if(head.value == null) {
			head = head.next;
			size--;
			serial++;
			return true;
		}
		
		for(Node node = head;node.next != null;node = node.next) {
			
			if(node.next.value == null) {
				node.next = node.next.next;
				size--;
				serial++;
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public boolean remove(T value) {
		
		if(size == 0)
			return false;
	
		if(value == null)
			return removeNull();
		
		if(value.equals(head.value)) {
			head = head.next;
			size--;
			serial++;
			return true;
		}
		
		for(Node node = head;node.next != null;node = node.next) {
			
			if(value.equals(node.next.value)) {
				node.next = node.next.next;
				size--;
				serial++;
				return true;
			}
			
		}
		
		return false;
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		size = 0;
		head = null;
		serial++;
	}

	@Override
	public boolean contains(T value) {
		return find(value) != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		
		Object[] array = new Object[size];
		
		Node node = head;
		
		for(int i = 0;i < size;i++) {
			array[i] = node.value;
			node = node.next;
		}
		
		return (T[]) array;
	}
	
	private int countNull() {
		
		int count = 0;
		
		for(Node node = head;node != null;node = node.next) {
			if(node.value == null)
				count++;
		}
		
		return count;
	}

	@Override
	public int count(T value) {
		
		if(value == null) {
			return countNull();
		}
		
		int count = 0;
		
		for(Node node = head;node != null;node = node.next) {
			if(value.equals(node.value))
				count++;
		}
		
		return count;
	}

	@Override
	public void insert(int index, T value) {
		ErrorChecks.indexCheck(index, 0, size+1);
		
		if(index == 0) {
			
			final Node tmp = head;
			head = new Node(value);
			head.next = tmp;
			
		} else {
			
			Node node = head;
			
			for(int i = 0;i < index-1;i++) {
				node = node.next;
			}
			
			final Node tmp = node;
			node = new Node(value);
			node.next = tmp.next;
			tmp.next = node;
		}
		
		size++;
		serial++;
	}
	
	private Node find(int index) {
		
		Node node = head;
		
		for(int i = 0;i < index;i++) {
			node = node.next;
		}
	
		return node;
	}
	
	private Node findNull() {
		
		for(Node node = head;node != null;node = node.next) {
			if(node.value == null)
				return node;
		}
		
		return null;
		
	}
	
	private Node find(T value) {
		
		if(value == null)
			return findNull();
		
		for(Node node = head;node != null;node = node.next) {
			if(value.equals(node.value))
				return node;
		}
		
		return null;
	}

	@Override
	public T replaceAt(int index, T value) {
		ErrorChecks.indexCheck(index, 0, size);
		
		Node node = find(index);
		
		final T old = node.value;
		
		node.value = value;
		
		return old;
	}

	@Override
	public void replace(T old, T repl) {
		
		Node node = find(old);
		
		if(node != null) {
			node.value = repl;
		}
		
	}

	@Override
	public void swap(int i, int j) {
		ErrorChecks.indexCheck(i, 0, size);
		ErrorChecks.indexCheck(j, 0, size);		
		
		Node a = null;
		Node b = null;
		Node node = head;
		
		for(int k = 0;k <= Math.max(i, j);k++) {
			
			if(k == i) {
				a = node;
			} 
			if(k == j) {
				b = node;
			}
			
			node = node.next;
		}
		
		final T tmp = a.value;
		a.value = b.value;
		b.value = tmp;
	}

	@Override
	public void addFirst(T value) {
		add(value);
	}

	@Override
	public void addLast(T value) {
		if(size == 0) {
			head = new Node(value);
		} else {
			
			Node node = head;
			
			while(node.next != null) {
				node = node.next;
			}
			
			node.next = new Node(value);
		}
		
		size++;
		serial++;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T> {
		
		Node current = head;
		final int mods = serial;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			
			if(mods != serial) {
				throw new ConcurrentModificationException();
			}
			
			final T value = current.value;
			current = current.next;
			return value;
		}
		
		
	}

	@Override
	public String toString() {	
		StringBuilder builder = new StringBuilder("[");
		
		for(Node node = head;node != null;node = node.next) {
			builder.append(node.value);
			if(node.next != null) {
				builder.append(", ");
			}
		}
		
		
		return builder.append(']').toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + serial;
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LinkedList) {
			
			LinkedList<T> other = (LinkedList<T>) obj;
			
			if(size != other.size)
				return false;
			
			if(size == 0)
				return true;
			
			Node a = head;
			Node b = other.head;
			
			while(a != null) {
				if(a.value == null) {
					if(b.value != null)
						return false;
				} else if(!a.value.equals(b.value)) {
					return false;
				}
				a = a.next;
				b = b.next;
			}
			
			return true;
		}
		
		return false;
	}
	
	
	
}

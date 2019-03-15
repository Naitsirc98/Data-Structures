package datastructures.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.restrictive.Deque;
import datastructures.util.ErrorChecks;

public class ArrayList<T> implements List<T>, Deque<T> {

	private Object[] array;
	private int size;
	private int serial = Integer.MIN_VALUE;

	private void init(int initialCapacity) {
		ErrorChecks.assertThat(initialCapacity >= 0, "Capacity must be >= 0");
		array = new Object[initialCapacity];
	}

	public ArrayList(int initialCapacity) {
		init(initialCapacity);
	}

	@SafeVarargs
	public ArrayList(T...values) {
		if(values.length > 0) {
			init(values.length);
			addAll(values);
		} else {
			init(10);
		}
	}

	public ArrayList(Iterable<T> other) {
		this(10);
		addAll(other);
	}

	private int indexOfNull() {

		for(int i = 0;i < size;i++) {
			if(array[i] == null)
				return i;
		}

		return -1;
	}

	@Override
	public int indexOf(T value) {

		if(value == null) {
			return indexOfNull();
		} 

		for(int i = 0;i < size;i++) {
			if(value.equals(array[i]))
				return i;
		}

		return -1;
	}

	@SuppressWarnings("unchecked")
	private T at(int index) {
		return (T) array[index];
	}

	@Override
	public T first() {
		ErrorChecks.assertThat(size > 0, NoSuchElementException.class);
		return at(0);
	}

	@Override
	public T last() {
		ErrorChecks.assertThat(size > 0, NoSuchElementException.class);
		return at(size-1);
	}

	@Override
	public T get(int index) {
		ErrorChecks.indexCheck(index, 0, size);
		return at(index);
	}

	@Override
	public T removeAt(int index) {
		ErrorChecks.indexCheck(index, 0, size);
		
		final T value = at(index);
		
		for(int i = index;i < size-1;i++) {
			array[i] = array[i+1];
		}
		
		array[size-1] = null;
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

		AbstractList<T> result = new ArrayList<>();

		for(int i = min;i < max;i++) {
			result.add(at(i));
		}

		return result;
	}

	public void resize(int newCapacity) {
		ErrorChecks.assertThat(newCapacity >= 0);
		array = Arrays.copyOf(array, newCapacity);
		if(newCapacity < size)
			size = newCapacity;
	}

	@Override
	public boolean add(T value) {

		if(size == capacity()) {
			resize(size * 2);
		}

		array[size++] = value;
		
		serial++;

		return true;
	}

	private boolean removeNull() {

		for(int i = 0;i < size;i++) {

			if(array[i] == null) {

				for(int j = i;j < size-1;j++) {
					array[j] = array[j+1];
				}

				array[size-1] = null;
				size--;
				return true;

			}

		}
		
		serial++;

		return false;
	}

	@Override
	public boolean remove(T value) {

		if(value == null) {
			return removeNull();
		}

		for(int i = 0;i < size;i++) {

			if(value.equals(array[i])) {

				for(int j = i;j < size-1;j++) {
					array[j] = array[j+1];
				}

				array[size-1] = null;
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

	public int capacity() {
		return array.length;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void clear() {
		
		for(int i = 0;i < size;i++) {
			array[i] = null;
		}
		
		size = 0;
		serial++;
	}

	@Override
	public boolean contains(T value) {
		return indexOf(value) >= 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		return (T[]) Arrays.copyOf(array, size);
	}

	private int lastIndexOfNull() {

		for(int i = size-1;i >= 0;i--) {
			if(array[i] == null)
				return i;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(T value) {
		if(value == null) {
			return lastIndexOfNull();
		}

		for(int i = size-1;i >= 0;i--) {
			if(value.equals(array[i])) 
				return i;
		}

		return -1;
	}

	private int countOfNull() {

		int count = 0;

		for(int i = 0;i < size;i++) {
			if(array[i] == null)
				count++;
		}

		return count;
	}

	@Override
	public int count(T value) {

		if(value == null)
			return countOfNull();

		int count = 0;

		for(int i = 0;i < size;i++) {
			if(value.equals(array[i]))
				count++;
		}

		return count;
	}

	@Override
	public void insert(int index, T value) {
		ErrorChecks.indexCheck(index, 0, size+1);
		
		if(size == capacity())
			resize(size*2);
			
		System.arraycopy(array, index, array, index+1, size-index);
		
		array[index] = value;
		size++;
		
		serial++;
	}
	
	@Override
	public T replaceAt(int index, T value) {
		ErrorChecks.indexCheck(index, 0, size);
		
		final T old = at(index);
		
		array[index] = value;
		serial++;
		
		return old;
	}
	
	@Override
	public void replace(T old, T repl) {
		final int index = indexOf(old);
		
		if(index >= 0) {
			array[index] = repl;
			serial++;
		}
		
	}
	
	@Override
	public void swap(int i, int j) {
		ErrorChecks.indexCheck(i, 0, size);
		ErrorChecks.indexCheck(j, 0, size);
		
		final Object tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	@Override
	public void addFirst(T value) {
		if(size == 0)
			add(value);
		else
			insert(0, value);
	}

	@Override
	public void addLast(T value) {
		add(value);
	}
	
	@Override
	public boolean push(T value) {
		addLast(value);
		return true;
	}

	@Override
	public T pop() {
		return removeAt(size-1);
	}

	@Override
	public T peek() {
		return last();
	}

	@Override
	public boolean enqueue(T value) {
		addLast(value);
		return true;
	}

	@Override
	public T poll() {
		return removeAt(0);
	}


	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {
		
		private int index = 0;
		private final int mods = serial;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			
			if(mods != serial) {
				throw new ConcurrentModificationException();
			}
			
			return at(index++);
		}
		
	}

	@Override
	public String toString() {	
		StringBuilder builder = new StringBuilder("[");
		
		for(int i = 0;i < size;i++) {
			builder.append(array[i]);
			if(i != size-1) {
				builder.append(", ");
			}
		}
		
		return builder.append(']').toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		result = prime * result + serial;
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ArrayList) {
			
			@SuppressWarnings("unchecked")
			ArrayList<T> other = (ArrayList<T>) obj;
			
			if(other.size == size) {
				
				if(size == 0)
					return true;
				
				for(int i = 0;i < size;i++) {
					Object value = array[i];
					
					if(value == null) {
						if(other.array[i] != null)
							return false;
					} else if(!value.equals(array[i])) {
						return false;
					}
					
				}
				
				return true;
				
			}
			
		}
		
		return false;
	}
	
	

}

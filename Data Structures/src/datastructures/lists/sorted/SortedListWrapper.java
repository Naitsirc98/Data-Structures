package datastructures.lists.sorted;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import datastructures.lists.AbstractList;
import datastructures.lists.List;
import datastructures.restrictive.PriorityQueue;
import datastructures.util.Algorithms;
import datastructures.util.ErrorChecks;

public class SortedListWrapper<T> implements SortedList<T>, PriorityQueue<T> {

	protected List<T> list;
	protected Comparator<T> comparator;
	
	public SortedListWrapper(List<T> list) {
		ErrorChecks.assertNotNull(list);
		this.list = list;
		comparator = (a, b) -> {
			
			final int h1 = a == null ? Integer.MIN_VALUE : a.hashCode();
			final int h2 = b == null ? Integer.MIN_VALUE : b.hashCode();
			
			return Integer.compare(h1, h2);
		};
	}
	
	public SortedListWrapper(List<T> list, Comparator<T> comparator) {
		ErrorChecks.assertNotNull(list);
		ErrorChecks.assertNotNull(comparator);
		this.list = list;
		this.comparator = comparator;
	}
	
	@SafeVarargs
	public SortedListWrapper(List<T> list, T...values) {
		this(list);
		addAll(values);
	}

	public SortedListWrapper(List<T> list, Iterable<T> other) {
		this(list);
		addAll(other);
	}
	
	public SortedListWrapper(List<T> list, Iterable<T> other, Comparator<T> comparator) {
		this(list, comparator);
		addAll(other);
	}

	@Override
	public int indexOf(T value) {
		return Algorithms.binarySearch(value, comparator, list);
	}
	
	@Override
	public int lastIndexOf(T value) {
		return list.lastIndexOf(value);
	}

	@Override
	public int count(T value) {
		return list.count(value);
	}

	@Override
	public T first() {
		return first();
	}

	@Override
	public T last() {
		return last();
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public T removeAt(int index) {
		return list.removeAt(index);
	}

	@Override
	public AbstractList<T> sublist(int min, int max) {
		return list.sublist(min, max);
	}

	@Override
	public boolean add(T value) {
		final int index = Math.abs(indexOf(value)+1);
		list.insert(index, value);
		return true;
	}

	@Override
	public boolean remove(T value) {
		return list.remove(value);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(T value) {
		return indexOf(value) >= 0;
	}

	@Override
	public T[] toArray() {
		return list.toArray();
	}

	@Override
	public Comparator<T> getComparator() {
		return comparator;
	}

	@Override
	public void setComparator(Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		this.comparator = comparator;
		Algorithms.quickSort(list, comparator);
	}

	@Override
	public AbstractList<T> lower(T value) {
		final int index = indexOf(value);
		if(index < 0)
			return null;
		return sublist(0, index);
	}

	@Override
	public AbstractList<T> higher(T value) {
		final int index = indexOf(value);
		if(index < 0)
			return null;
		return sublist(index, list.size());
	}
	
	@Override
	public boolean enqueue(T value) {
		return add(value);
	}

	@Override
	public T poll() {
		return removeAt(0);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public String toString() {
		return list.toString();
	}
	
}

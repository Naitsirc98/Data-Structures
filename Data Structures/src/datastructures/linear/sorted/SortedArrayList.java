package datastructures.linear.sorted;

import java.util.Comparator;
import java.util.Iterator;

import datastructures.linear.AbstractList;
import datastructures.linear.ArrayList;
import datastructures.util.Algorithms;
import datastructures.util.ErrorChecks;

public class SortedArrayList<T> implements SortedList<T> {
	
	private ArrayList<T> list;
	private Comparator<T> comparator;
	
	public SortedArrayList() {
		list = new ArrayList<>();
		comparator = (a, b) -> Integer.compare(a.hashCode(), b.hashCode());
	}
	
	public SortedArrayList(Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		list = new ArrayList<>();
		this.comparator = comparator;
	}
	
	@SafeVarargs
	public SortedArrayList(T...values) {
		this();
		addAll(values);
	}

	public SortedArrayList(Iterable<T> other) {
		this();
		addAll(other);
	}
	
	public SortedArrayList(Iterable<T> other, Comparator<T> comparator) {
		this(comparator);
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
		final int index = Math.abs(indexOf(value))-1;
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
	public Iterator<T> iterator() {
		return list.iterator();
	}

}

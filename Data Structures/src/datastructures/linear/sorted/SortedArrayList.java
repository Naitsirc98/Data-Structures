package datastructures.linear.sorted;

import java.util.Comparator;

import datastructures.linear.ArrayList;

public class SortedArrayList<T> extends SortedListWrapper<T> {
	
	public SortedArrayList() {
		super(new ArrayList<>());
	}
	
	public SortedArrayList(Comparator<T> comparator) {
		super(new ArrayList<>(), comparator);
	}
	
	@SafeVarargs
	public SortedArrayList(T...values) {
		super(new ArrayList<>(), values);
	}

	public SortedArrayList(Iterable<T> other) {
		super(new ArrayList<>(), other);
	}
	
	public SortedArrayList(Iterable<T> other, Comparator<T> comparator) {
		super(new ArrayList<>(), other, comparator);
	}

}

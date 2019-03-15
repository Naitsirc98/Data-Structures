package datastructures.lists.sorted;

import datastructures.SortedCollection;
import datastructures.lists.AbstractList;

public interface SortedList<T> extends AbstractList<T>, SortedCollection<T> {

	AbstractList<T> lower(T value);
	AbstractList<T> higher(T value);
	
}

package datastructures;

import java.util.Comparator;

public interface SortedCollection<T> extends AbstractCollection<T> {

	Comparator<T> getComparator();
	void setComparator(Comparator<T> comparator);
	T first();
	T last();
	
}

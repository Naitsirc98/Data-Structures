package datastructures.linear;

import datastructures.Collection;

public interface AbstractList<T> extends Collection<T> {
	
	int indexOf(T value);
	T first();
	T last();
	T get(int index);
	T removeAt(int index);
	AbstractList<T> sublist(int min, int max);

}

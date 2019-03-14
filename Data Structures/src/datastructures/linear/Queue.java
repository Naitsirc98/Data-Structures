package datastructures.linear;

import datastructures.AbstractCollection;

public interface Queue<T> extends AbstractCollection<T> {

	boolean enqueue(T value);
	T poll();
	T first();
	T last();
	
}

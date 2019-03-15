package datastructures.restrictive;

import datastructures.AbstractCollection;

public interface Stack<T> extends AbstractCollection<T> {

	boolean push(T value);
	T pop();
	T peek();

}

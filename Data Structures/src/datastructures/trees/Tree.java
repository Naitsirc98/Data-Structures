package datastructures.trees;

import java.util.Iterator;

import datastructures.Collection;

public interface Tree<T> extends Collection<T> {
	
	T root();
	
	int level(T value);
	int degree(T value);
	int degree();
	int height();
	int height(T value);

	// Depth first traversal
	Iterator<T> inOrderIterator();
	Iterator<T> preOrderIterator();
	Iterator<T> postOrderIterator();
	
	// Breath first / Level order traversal
	Iterator<T> breathIterator();
	
	@Override
	default Iterator<T> iterator() {
		return inOrderIterator();
	}

}

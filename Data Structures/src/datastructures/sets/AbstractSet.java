package datastructures.sets;

import datastructures.AbstractCollection;

public interface AbstractSet<T> extends AbstractCollection<T> {
	
	AbstractSet<T> union(AbstractSet<T> other);
	AbstractSet<T> intersection(AbstractSet<T> other);
	AbstractSet<T> complement(AbstractSet<T> other);

}

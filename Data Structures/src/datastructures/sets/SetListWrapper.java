package datastructures.sets;

import java.util.Iterator;

import datastructures.AbstractCollection;
import datastructures.lists.List;
import datastructures.util.ErrorChecks;

public class SetListWrapper<T> implements Set<T> {
	
	private List<T> list;
	
	public SetListWrapper(List<T> list) {
		ErrorChecks.assertNotNull(list);
		ErrorChecks.assertThat(list.isEmpty(), "List must be empty!");
		this.list = list;
	}
	
	@SafeVarargs
	public SetListWrapper(List<T> list, T...values) {
		this(list);
		addAll(values);
	}
	
	public SetListWrapper(List<T> list, Iterable<T> other) {
		this(list);
		addAll(other);
	}

	@Override
	public AbstractSet<T> union(AbstractSet<T> other) {
		Set<T> result = (Set<T>) copy();
		result.addAll(other);
		return result;
	}

	@Override
	public AbstractSet<T> intersection(AbstractSet<T> other) {
		Set<T> result = (Set<T>) copy();
		for(T value : result) {
			if(!other.contains(value)) {
				result.remove(value);
			}
		}
		return result;
	}

	@Override
	public AbstractSet<T> complement(AbstractSet<T> other) {
		Set<T> result = (Set<T>) copy();
		for(T value : result) {
			if(other.contains(value)) {
				result.remove(value);
			}
		}
		return result;
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
		return list.contains(value);
	}

	@Override
	public T[] toArray() {
		return list.toArray();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public boolean add(T value) {
		if(!list.contains(value)) {
			return list.add(value);
		}
		return false;
	}

	@Override
	public boolean remove(T value) {
		return list.remove(value);
	}

	@Override
	public AbstractCollection<T> copy() {
		return new SetListWrapper<>((List<T>) list.copy(), this);
	}
	

}

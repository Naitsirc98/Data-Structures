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
		for(T value : other) {
			result.remove(value);
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
		List<T> list = (List<T>) this.list.copy();
		list.clear();
		return new SetListWrapper<>(list, this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SetListWrapper) {
			@SuppressWarnings("unchecked")
			SetListWrapper<T> other = (SetListWrapper<T>) obj;
			
			for(T value : other) {
				if (!contains(value)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return list.toString();
	}
	
	

}

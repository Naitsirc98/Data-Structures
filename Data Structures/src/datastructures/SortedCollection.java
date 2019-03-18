package datastructures;

import java.util.Comparator;

public interface SortedCollection<T> extends AbstractCollection<T> {
	
	static <T> Comparator<T> naturalComparator() {
		return (a, b) -> {
			
			final int h1 = a == null ? Integer.MIN_VALUE : a.hashCode();
			final int h2 = b == null ? Integer.MIN_VALUE : b.hashCode();
			
			return Integer.compare(h1, h2);
		};
	}
	
	static <T> Comparator<T> reverseComparator() {
		return (a, b) -> {
			
			final int h1 = a == null ? Integer.MIN_VALUE : a.hashCode();
			final int h2 = b == null ? Integer.MIN_VALUE : b.hashCode();
			
			return Integer.compare(h2, h1);
		};
	}

	Comparator<T> getComparator();
	void setComparator(Comparator<T> comparator);
	T first();
	T last();
	
}

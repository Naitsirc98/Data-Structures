package datastructures.util;

import java.util.Comparator;

import datastructures.lists.AbstractList;
import datastructures.lists.List;

public final class Algorithms {

	private Algorithms() {}

	public static <T> void quickSort(List<T> list, Comparator<T> cmp) {
		quickSort(list, cmp, 0, list.size()-1);
	}

	public static <T> void quickSort(List<T> list, Comparator<T> cmp, int begin, int end) {

		if(begin < end) {

			final int index = partition(list, cmp, begin, end);

			quickSort(list, cmp, begin, index-1);
			quickSort(list, cmp, index+1, end);

		}

	}

	private static <T> int partition(List<T> list, Comparator<T> cmp, int begin, int end) {

		T pivot = list.get(end);
		int i = begin - 1;

		for(int j = begin;j < end;j++) {

			if(cmp.compare(list.get(j), pivot) <= 0) {

				i++;

				list.swap(i, j);

			}

		}

		i++;

		list.swap(i, end);

		return i;
	}


	public static <T> int binarySearch(int from, int to, T value, Comparator<T> cmp, AbstractList<T> list) {
		
		while(from <= to) {

			final int mid = (from+to)/2;
			final T midValue = list.get(mid);

			if(cmp.compare(value, midValue) < 0) {
				to = mid-1;
			} else if(cmp.compare(value, midValue) > 0) {
				from = mid+1;
			} else {
				return mid;
			}			

		}
		return -from-1;

	}

	public static <T> int binarySearch(T value, Comparator<T> cmp, AbstractList<T> list) {
		return binarySearch(0, list.size()-1, value, cmp, list);
	}
	
	public static double average(double[] values) {
		ErrorChecks.assertNotNull(values);
		ErrorChecks.assertThat(values.length > 0, "Array is empty");
		double sum = 0.0f;
		for(double v : values)
			sum += v;
		return sum / values.length;
	}
	
	public static <T> T max(AbstractList<T> list, Comparator<T> cmp) {
		ErrorChecks.assertNotNull(list);
		
		if(list.isEmpty()) {
			return null;
		}
		
		T max = list.get(0);
		
		for(int i = 1;i < list.size();i++) {
			if(cmp.compare(max, list.get(i)) < 0) {
				max = list.get(i);
			}
		}
		
		return max;
	}

}

package datastructures.trees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.AbstractCollection;
import datastructures.SortedCollection;
import datastructures.lists.ArrayList;
import datastructures.restrictive.PriorityQueue;
import datastructures.trees.BinaryTree.BinaryTreeNode;
import datastructures.util.ErrorChecks;

public class Heap<T> implements BalancedTree<T>, PriorityQueue<T> {
	
	public static <T> Heap<T> createMinHeap() {
		return new Heap<T>(SortedCollection.naturalComparator());
	}
	
	public static <T> Heap<T> createMaxHeap() {
		return new Heap<T>(SortedCollection.reverseComparator());
	}
	
	private ArrayList<T> array;
	private Comparator<T> comparator;

	public Heap() {
		this(SortedCollection.naturalComparator());
	}

	public Heap(T root) {
		this(root, SortedCollection.naturalComparator());
	}

	public Heap(Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		this.comparator = comparator;
		array = new ArrayList<>();
	}

	public Heap(T root, Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		this.comparator = comparator;
		array = new ArrayList<>(root);
	}

	public Heap(Iterable<T> other) {
		this(SortedCollection.naturalComparator());
		addAll(other);
	}

	public Heap(Comparator<T> comparator, Iterable<T> other) {
		this(null, comparator);
		addAll(other);
	}

	public Heap(T root, Comparator<T> comparator, Iterable<T> other) {
		this(root, comparator);
		addAll(other);
	}

	@Override
	public Comparator<T> getComparator() {
		return comparator;
	}

	@Override
	public void setComparator(Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		if(!this.comparator.equals(comparator)) {
			// TODO
		}
	}

	@Override
	public T root() {
		return array.first();
	}

	@Override
	public int level(T value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degree(T value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height(T value) {
		return 0;
	}
	
	private int parentOf(int index) {
		return (index-1)/2;
	}
	
	private int leftChildOf(int index) {
		return (2*index)+1;
	}
	
	private int rightChildOf(int index) {
		return (2*index)+2;
	}
	
	private void siftUp(int index) {
		
		while(index != 0) {
			
			final int parentIndex = parentOf(index);
			
			if(comparator.compare(array.get(index), array.get(parentIndex)) < 0) {
				array.swap(index, parentIndex);
			} else {
				break;
			}
			
			index = parentIndex;
		}
		
	}

	@Override
	public boolean add(T value) {
		
		array.add(value);
		
		siftUp(array.size()-1);
		
		return true;
	}

	@Override
	public boolean remove(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public void clear() {
		array.clear();
	}

	@Override
	public boolean contains(T value) {
		return false;
	}

	@Override
	public T[] toArray() {
		return array.toArray();
	}

	@Override
	public AbstractCollection<T> copy() {
		return new Heap<>(array);
	}

	@Override
	public boolean enqueue(T value) {
		return add(value);
	}

	@Override
	public T poll() {
		ErrorChecks.assertThat(!array.isEmpty(), NoSuchElementException.class);
		final T value = array.get(0);
		remove(value);
		return value;
	}

	@Override
	public T first() {
		return array.first();
	}

	@Override
	public T last() {
		return array.last();
	}

	@Override
	public Iterator<T> inOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> preOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> postOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> breathIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram (Todd Davies answer)*/
	protected StringBuilder visualize(int node, StringBuilder prefix, boolean isTail, StringBuilder sb) {

		int right = rightChildOf(node);
		int left = leftChildOf(node);
		
	    if(right < size() && array.get(right) != null) {
	        visualize(right, new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "\\—— " : "/—— ").append(array.get(node)).append("\n");
	    if(left < size() && array.get(left) != null) {
	        visualize(left, new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb);
	    }
	    return sb;
	}
	

	@Override
	public String toString() {
	    return !array.isEmpty() 
	    		? visualize(0, new StringBuilder(), true, new StringBuilder()).toString()
	    		: "{}";
	}

}

package datastructures.trees;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.AbstractCollection;
import datastructures.SortedCollection;
import datastructures.lists.ArrayList;
import datastructures.lists.LinkedList;
import datastructures.restrictive.PriorityQueue;
import datastructures.restrictive.Stack;
import datastructures.trees.BinaryTree.BinaryTreeNode;
import datastructures.util.Algorithms;
import datastructures.util.ErrorChecks;
import datastructures.util.Pair;

public class Heap<T> implements BalancedTree<T>, PriorityQueue<T> {
	
	public static <T> Heap<T> createMinHeap() {
		return new Heap<T>(SortedCollection.naturalComparator());
	}
	
	public static <T> Heap<T> createMaxHeap() {
		return new Heap<T>(SortedCollection.reverseComparator());
	}
	
	private ArrayList<T> array;
	private Comparator<T> comparator;
	private int serial = Integer.MIN_VALUE;

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
			this.comparator = comparator;
			final T[] arr = array.toArray();
			clear();
			addAll(arr);
		}
	}

	@Override
	public T root() {
		return array.first();
	}

	@Override
	public int level(T value) {
		
		int level = 1;
		
		for(float parent = parentOf((float)indexOf(value));
				parent >= 0.0f;parent = parentOf(parent)) {
			level++;
		}
		
		return level;
	}
	
	private int degree(int index) {
		
		if(index < 0)
			return -1;

		int left = leftChildOf(index);
		int right = rightChildOf(index);
		
		int degree = 0;
		
		if(left < size() && array.get(left) != null) {
			degree += 1 + degree(left);
		}
		
		if(right < size() && array.get(right) != null) {
			degree += 1 + degree(right);
		}
		
		return degree;
	}
	
	private int indexOf(T value) {
		return array.indexOf(value);
	}

	@Override
	public int degree(T value) {
		return degree(indexOf(value));
	}

	@Override
	public int degree() {
		return array.isEmpty() ? 0 : degree(array.get(0));
	}

	@Override
	public int height() {
		return size() == 0 ? 0 : height(indexOf(array.get(0)));
	}
	
	private int height(int index) {
		
		if(index < 0 || index >= size()) {
			return 0;
		}
		
		return 1 + Math.max(height(leftChildOf(index)) , height(rightChildOf(index)));
	}

	@Override
	public int height(T value) {
		return height(indexOf(value));
	}
	
	private float parentOf(float index) {
		return (index-1)/2;
	}
	
	private int parentOf(int index) {
		return (int) parentOf((float)index);
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
		
		serial++;
		
		return true;
	}

	@Override
	public boolean remove(T value) {
		return removeAt(indexOf(value));
	}
	
	private boolean removeAt(int node) {
		
		if(node < 0)
			return false;
		
		array.replaceAt(node, array.last());
		array.removeAt(size()-1);
		
		siftDown(node);
		
		serial++;
		return true;
	}
	
	private void siftDown(int node) {

		int min = min(leftChildOf(node), rightChildOf(node));
		
		while(min >= 0) {
			
			if(comparator.compare(array.get(node), array.get(min)) > 0) {
				array.swap(node, min);
				node = min;
				min = min(leftChildOf(min), rightChildOf(min));
			} else {
				break;
			}
			
		}
		
	}
	
	private int min(int left, int right) {
		
		if(left >= size()) {
			if(right < size()) {
				return right;
			} else {
				return -1;
			}
		}
		
		if(right >= size()) {
			if(left < size()) {
				return left;
			} else {
				return -1;
			}
		}
		
		final int cmp = comparator.compare(array.get(left), array.get(right));
		
		if(cmp <= 0) {
			return left;
		} 
		
		return right;
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
		return array.contains(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		Object[] arr = new Object[size()];
		
		for(int i = 0;i < arr.length;i++) {
			arr[i] = array.get(i);
		}
		
		return (T[]) arr;
	}

	@Override
	public AbstractCollection<T> copy() {
		return new Heap<>(this);
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
		return Algorithms.max(array, comparator);
	}

	@Override
	public Iterator<T> inOrderIterator() {
		return new InOrderBinaryTreeIterator();
	}

	@Override
	public Iterator<T> preOrderIterator() {
		return new PreOrderHeapIterator();
	}

	@Override
	public Iterator<T> postOrderIterator() {
		return new PostOrderHeapIterator();
	}

	@Override
	public Iterator<T> breathIterator() {
		return new HeapBreathIterator();
	}
	
	protected class InOrderBinaryTreeIterator implements Iterator<T> {

		Stack<Integer> stack = new LinkedList<>();
		protected final int mods = serial;

		InOrderBinaryTreeIterator() {
			this(0);
		}

		InOrderBinaryTreeIterator(int node) {
			insertLeft(node);
		}

		void insertLeft(int node) {
			while(node < size()) {
				stack.push(node);
				node = leftChildOf(node);
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);

			int node = stack.pop();

			insertLeft(rightChildOf(node));

			return array.get(node);
		}

	}
	
	protected class PreOrderHeapIterator implements Iterator<T> {
		
		protected Stack<Integer> stack = new LinkedList<>();
		protected final int mods = serial;
		
		PreOrderHeapIterator() {
			this(0);
		}
		
		PreOrderHeapIterator(int node) {
			push(node);
		}
		
		private void push(int node) {
			if(node < size())
				stack.push(node);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);
			
			int node = stack.pop();
			
			push(rightChildOf(node));
			
			push(leftChildOf(node));
			
			return array.get(node);
		}
		
	}
	
	protected class PostOrderHeapIterator implements Iterator<T> {

		Stack<Pair<Integer, Boolean>> stack = new LinkedList<>();
		protected final int mods = serial;
		
		PostOrderHeapIterator() {
			this(0);
		}
		
		PostOrderHeapIterator(int node) {
			if(node < size())
				stack.push(new Pair<>(node, false));
		}
		
		private void push(int node) {
			if(node < size())
				stack.push(new Pair<>(node, false));
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);
			
			Pair<Integer, Boolean> pair = stack.peek();
			
			if(pair.second) {
				stack.pop();
				return array.get(pair.first);
				
			} else {
				
				int node = pair.first;
				
				push(rightChildOf(node));
				
				push(leftChildOf(node));
				
				pair.second = true;
				
			}
			
			return next();
		}
		
	}
	
	protected class HeapBreathIterator implements Iterator<T> {

		Iterator<T> it = array.iterator();
		
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public T next() {
			return it.next();
		}
		
	}
	
	
	/*https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram (Todd Davies answer)*/
	protected StringBuilder visualize(int node, StringBuilder prefix, boolean isTail, StringBuilder sb) {

		int right = rightChildOf(node);
		int left = leftChildOf(node);
		
	    if(right < size()) {
	        visualize(right, new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "\\—— " : "/—— ").append(array.get(node)).append("\n");
	    if(left < size()) {
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

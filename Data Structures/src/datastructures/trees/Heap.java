package datastructures.trees;

import java.util.Comparator;

import datastructures.SortedCollection;
import datastructures.util.ErrorChecks;

public class Heap<T> extends BinaryTree<T> implements BalancedTree<T>, SortedCollection<T> {
	
	protected class HeapNode extends BinaryTreeNode {
		
		protected static final boolean LEFT = false;
		protected static final boolean RIGHT = true;

		protected boolean nextSide = LEFT; // Used for balance
		
		protected HeapNode(BinaryTree<T>.BinaryTreeNode parent, T value) {
			super(parent, value);
		}
		
		protected boolean nextSide() {
			final boolean next = nextSide;
			nextSide = !nextSide;
			return next;
		}
		
	}
	
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
	}
	
	public Heap(T root, Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		this.comparator = comparator;
		this.root = new HeapNode(null, root);
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
		throw new UnsupportedOperationException("Cannot change comparator of a created Heap");
	}
	
	@Override
	protected BinaryTreeNode find(T value) {
		return find(root, value);
	}
	
	protected BinaryTreeNode find(BinaryTreeNode node, T value) {
		
		if(node == null)
			return null;
		
		final int cmp = comparator.compare(value, node.value);
		
		if(cmp < 0) { // Imposible, the element is not in the subheap
			return null;
		} else if(cmp > 0) {
			return nonNull(find(node.left, value), find(node.right, value));
		} 
		
		// Here, cmp == 0, then return node
		
		return node;
	}
	
	protected BinaryTreeNode nonNull(BinaryTreeNode a, BinaryTreeNode b) {
		return a == null ? b : a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(T value) {
		
		// Add a value randomly
		
		if(root == null) {
			root = new HeapNode(null, value);
		} else {
			
			HeapNode node = (HeapNode) root;
			
			while(true) {
				
				if(node.left == null) {
					node.left = new HeapNode(node, value);
					node = (HeapNode) node.left;
					break;
				} else if(node.right == null) {
					node.right = new HeapNode(node, value);
					node = (HeapNode) node.right;
					break;
				} else {
					
					if(node.nextSide() == HeapNode.LEFT) {
						node = (HeapNode) node.left;
					} else {
						node = (HeapNode) node.right;
					}
					
				}

			}
			
			adjust(node);
			
		}
		
		size++;
		serial++;
		return true;
	}
	
	protected void adjust(BinaryTreeNode node) {
		
		if(node == null) {
			return;
		}
		
		while(node.parent != null) {
			if(comparator.compare(node.value, node.parent.value) < 0) {
				T tmp = node.parent.value;
				node.parent.value = node.value;
				node.value = tmp;
			}
			node = node.parent;
		}
		
	}
	
	@Override
	protected boolean remove(BinaryTreeNode node) {
		
		if(node == null)
			return false;
		
		if(node.isLeaf()) {
			
			removeLeaf(node);
			
		} else if(node.degree() == 1) {
			
			removeDegree1(node);
			
		} else { // Remove degree 2
			
			BinaryTreeNode min = min(node.left, node.right);
			
			node.value = min.value;
			
			remove(min);
		}
		
		return true;
	}
	
	protected BinaryTreeNode min(BinaryTreeNode a, BinaryTreeNode b) {
		
		if(a == null && b == null) 
			return null;
		if(a == null)
			return b;
		if(b == null)
			return a;
		
		final int cmp = comparator.compare(a.value, b.value);
		
		return cmp <= 0 ? a : b;
	}
	
	@Override
	public T first() {
		return root();
	}

	@Override
	public T last() {
		return new InOrderBinaryTreeIterator().next();
	}
	
	
	
}

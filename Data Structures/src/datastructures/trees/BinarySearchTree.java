package datastructures.trees;

import java.util.Comparator;
import java.util.Iterator;

import datastructures.AbstractCollection;
import datastructures.SortedCollection;
import datastructures.util.ErrorChecks;

public class BinarySearchTree<T> extends BinaryTree<T> implements SortedCollection<T> {
	
	protected Comparator<T> comparator;
	
	public BinarySearchTree() {
		this(SortedCollection.naturalComparator());
	}
	
	public BinarySearchTree(T root) {
		this(root, SortedCollection.naturalComparator());
	}
	
	public BinarySearchTree(Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator);
		this.comparator = comparator;
	}
	
	public BinarySearchTree(T root, Comparator<T> comparator) {
		super(root);
		ErrorChecks.assertNotNull(comparator);
		this.comparator = comparator;
	}
	
	public BinarySearchTree(Iterable<T> other) {
		this(SortedCollection.naturalComparator());
		addAll(other);
	}
	
	public BinarySearchTree(Comparator<T> comparator, Iterable<T> other) {
		this(comparator);
		addAll(other);
	}
	
	public BinarySearchTree(T root, Comparator<T> comparator, Iterable<T> other) {
		this(root, comparator);
		addAll(other);
	}
	
	@SafeVarargs
	public BinarySearchTree(T...values) {
		this(SortedCollection.naturalComparator());
		addAll(values);
	}
	
	@SafeVarargs
	public BinarySearchTree(Comparator<T> comparator, T...values) {
		this(comparator);
		addAll(values);
	}
	
	@SafeVarargs
	public BinarySearchTree(T root, Comparator<T> comparator, T...values) {
		this(root, comparator);
		addAll(values);
	}
	
	@Override
	public Comparator<T> getComparator() {
		return comparator;
	}

	@Override
	public void setComparator(Comparator<T> comparator) {
		ErrorChecks.assertNotNull(comparator != null);
		final Iterable<T> copy = copy();
		clear();
		this.comparator = comparator;
		addAll(copy);
	}
	
	protected BinaryTreeNode moveTo(BinaryTreeNode node, T value) {
		
		if(node == null)
			return null;
		
		final int cmp = comparator.compare(value, node.value);
		
		if(cmp < 0) {
			return node.left;
		} else if(cmp > 0) {
			return node.right;
		}
		
		return node;
	}

	@Override
	public T first() {
		return min(root).value;
	}

	@Override
	public T last() {
		return max(root).value;
	}
	
	@Override
	protected BinaryTreeNode find(T value) {
		
		for(BinaryTreeNode node = root;node != null;node = moveTo(node, value)) {
			if(node.has(value))
				return node;
		}
		
		return null;
	}
	
	protected BinaryTreeNode min(BinaryTreeNode node) {
		return leftMost(node);
	}
	
	protected BinaryTreeNode max(BinaryTreeNode node) {
		return rightMost(node);
	}
	
	@Override
	public boolean add(T value) {
		
		if(size == 0) {
			root = new BinaryTreeNode(null, value);
		} else {
			
			BinaryTreeNode node = root;
			
			while(true) {
				
				final int cmp = comparator.compare(value, node.value);
				
				if(cmp < 0) {
					if(node.left == null) {
						node.left = new BinaryTreeNode(node, value);
						break;
					}
					
					node = node.left;
					
				} else if(cmp > 0) {
					if(node.right == null) {
						node.right = new BinaryTreeNode(node, value);
						break;
					}
					
					node = node.right;
					
				} else { // Already in the tree
					return false;
				}
				
				
			}
			
		}
		
		size++;
		serial++;
		return true;
	}
	
	@Override
	public AbstractCollection<T> copy() {
		
		Iterator<T> it = breathIterator();
		
		BinarySearchTree<T> copy = new BinarySearchTree<>();
		
		while(it.hasNext()) {
			copy.add(it.next());
		}
		
		return copy;
	}


}

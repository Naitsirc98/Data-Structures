package datastructures.trees;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

import datastructures.AbstractCollection;
import datastructures.lists.DoublyLinkedList;
import datastructures.lists.LinkedList;
import datastructures.restrictive.Queue;
import datastructures.restrictive.Stack;
import datastructures.util.ErrorChecks;
import datastructures.util.Pair;

public class BinaryTree<T> implements Tree<T> {
	
	protected class BinaryTreeNode {
		
		protected T value;
		protected BinaryTreeNode parent;
		protected BinaryTreeNode left, right;
		
		protected BinaryTreeNode(BinaryTreeNode parent, T value) {
			this.parent = parent;
			this.value = value;
		}
		
		protected boolean isLeaf() {
			return left == null && right == null;
		}
		
		protected int degree() {
			final int result = left == null ? 0 : 1;
			return result + (right == null ? 0 : 1);
		}
		
		protected boolean has(T value) {
			if(value == null)
				return this.value == null;
			else
				return value.equals(this.value);
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}

	}

	protected BinaryTreeNode root;
	protected int size;
	protected int serial = Integer.MIN_VALUE;
	
	public BinaryTree() {
		
	}
	
	public BinaryTree(T root) {
		this.root = new BinaryTreeNode(null, root);
	}

	public BinaryTree(Iterable<T> other) {
		addAll(other);
	}
	
	@SafeVarargs
	public BinaryTree(T...values) {
		addAll(values);
	}
	
	protected BinaryTreeNode find(T value) {
		
		Iterator<BinaryTreeNode> it = new BreathBinaryTreeNodeIterator();
		
		while(it.hasNext()) {
			BinaryTreeNode node = it.next();
			if(node.has(value))
				return node;
		}
		
		return null;
	}
	
	protected BinaryTreeNode leftMost(BinaryTreeNode node) {
		
		while(node != null && node.left != null) {
			node = node.left;
		}
		
		return node;
	}
	
	protected BinaryTreeNode rightMost(BinaryTreeNode node) {
		
		while(node != null && node.right != null) {
			node = node.right;
		}
		
		return node;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
		serial++;
	}


	@Override
	public boolean contains(T value) {
		return find(value) != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		Object[] arr = new Object[size];
		
		Iterator<T> it = inOrderIterator();
		
		for(int i = 0;i < size;i++) {
			arr[i] = it.next();
		}
		
		return (T[]) arr;
	}

	@Override
	public AbstractCollection<T> copy() {
		
		Iterator<T> it = breathIterator();
		
		BinaryTree<T> copy = new BinaryTree<>();
		
		while(it.hasNext()) {
			copy.add(it.next());
		}
		
		return copy;
	}

	@Override
	public T root() {
		return root == null ? null : root.value;
	}

	@Override
	public int level(T value) {
		int level = 0;
		
		for(BinaryTreeNode node = find(value);node != null;node = node.parent) {
			level++;
		}
		
		return level;
	}

	@Override
	public int degree(T value) {
		BinaryTreeNode node = find(value);
		return node == null ? -1 : node.degree();
	}

	@Override
	public int degree() {
		return root == null ? 0 : root.degree();
	}

	@Override
	public int height() {
		return root == null ? 0 : height(root.value);
	}

	@Override
	public int height(T value) {
		BinaryTreeNode node = find(value);
		
		if(node == null)
			return -1;
		
		return height(node);
	}
	
	private int height(BinaryTreeNode node) {
		
		if(node == null)
			return 0;
		
		if(node.isLeaf())
			return 0;
		
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	@Override
	public boolean add(T value) {
		
		// Add a value randomly
		
		if(root == null) {
			root = new BinaryTreeNode(null, value);
		} else {
			
			BinaryTreeNode node = root;
			
			Random rand = new Random(System.nanoTime());
			
			while(true) {
				
				if(node.left == null) {
					node.left = new BinaryTreeNode(node, value);
					break;
				} else if(node.right == null) {
					node.right = new BinaryTreeNode(node, value);
					break;
				} else {
					node = rand.nextBoolean() ? node.left : node.right;
				}
				
			}
			
		}
		
		size++;
		serial++;
		return true;
	}

	@Override
	public boolean remove(T value) {
		
		BinaryTreeNode node = find(value);
		
		if(!remove(node))
			return false;
		
		size--;
		serial++;
		return true;
	}
	
	protected boolean remove(BinaryTreeNode node) {
		
		if(node == null)
			return false;
		
		if(node.isLeaf()) {
			
			removeLeaf(node);
			
		} else if(node.degree() == 1) {
			
			removeDegree1(node);
			
		} else { // Remove degree 2
			
			BinaryTreeNode left = leftMost(node.right);
			
			node.value = left.value;
			
			remove(left);
		}
		
		return true;
	}

	protected void removeLeaf(BinaryTreeNode node) {
		
		if(node == root) {
			root = null;
		} else {
			BinaryTreeNode parent = node.parent;
			
			if(parent.left == node) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			
		}
	}
	
	protected void removeDegree1(BinaryTreeNode node) {
		
		if(node.left != null) {
			if(node == root) {
				root = node.left;
			} else {
				if(node.parent.left == node) {
					node.parent.left = node.left;
					node.left.parent = node.parent;
				} else {
					node.parent.right = node.left;
					node.left.parent = node.parent;
				}
			}
		} else {
			if(node == root) {
				root = node.right;
			} else {
				if(node.parent.left == node) {
					node.parent.left = node.right;
					node.right.parent = node.parent;
				} else {
					node.parent.right = node.right;
					node.right.parent = node.parent;
				}
			}
		}
		
	}

	@Override
	public Iterator<T> inOrderIterator() {
		return new InOrderBinaryTreeIterator();
	}

	@Override
	public Iterator<T> preOrderIterator() {
		return new PreOrderBinaryTreeIterator();
	}

	@Override
	public Iterator<T> postOrderIterator() {
		return new PostOrderBinaryTreeIterator();
	}

	@Override
	public Iterator<T> breathIterator() {
		return new BreathBinaryTreeIterator();
	}
	
	protected class InOrderBinaryTreeIterator implements Iterator<T> {

		Stack<BinaryTreeNode> stack = new LinkedList<>();
		protected final int mods = serial;

		InOrderBinaryTreeIterator() {
			this(root);
		}

		InOrderBinaryTreeIterator(BinaryTreeNode node) {
			if(node != null)
				insertLeft(node);
		}

		void insertLeft(BinaryTreeNode node) {
			while(node != null) {
				stack.push(node);
				node = node.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);

			BinaryTreeNode node = stack.pop();

			if(node.right != null) {
				insertLeft(node.right);
			}

			return node.value;
		}

	}

	protected class BreathBinaryTreeIterator implements Iterator<T> {

		Queue<BinaryTreeNode> queue = new DoublyLinkedList<>();
		protected final int mods = serial;

		BreathBinaryTreeIterator() {
			this(root);
		}

		BreathBinaryTreeIterator(BinaryTreeNode node) {
			if(node != null)
				queue.enqueue(node);
		}

		void insertChildren(BinaryTreeNode node) {

			if(node.left != null)
				queue.enqueue(node.left);

			if(node.right != null)
				queue.enqueue(node.right);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);
			
			BinaryTreeNode node = queue.poll();

			insertChildren(node);

			return node.value;
		}

	}
	
	protected class BreathBinaryTreeNodeIterator implements Iterator<BinaryTreeNode> {

		Queue<BinaryTreeNode> queue = new DoublyLinkedList<>();
		protected final int mods = serial;

		BreathBinaryTreeNodeIterator() {
			this(root);
		}

		BreathBinaryTreeNodeIterator(BinaryTreeNode node) {
			if(node != null)
				queue.enqueue(node);
		}

		void insertChildren(BinaryTreeNode node) {

			if(node.left != null)
				queue.enqueue(node.left);

			if(node.right != null)
				queue.enqueue(node.right);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public BinaryTreeNode next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);
			
			BinaryTreeNode node = queue.poll();

			insertChildren(node);

			return node;
		}

	}
	
	protected class PreOrderBinaryTreeIterator implements Iterator<T> {
		
		protected Stack<BinaryTreeNode> stack = new LinkedList<>();
		protected final int mods = serial;
		
		PreOrderBinaryTreeIterator() {
			this(root);
		}
		
		PreOrderBinaryTreeIterator(BinaryTreeNode node) {
			if(node != null)
				stack.push(node);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);
			
			BinaryTreeNode node = stack.pop();
			
			if(node.right != null) {
				stack.push(node.right);
			}
			if(node.left != null) {
				stack.push(node.left);
			}
			
			return node.value;
		}
		
	}
	
	protected class PostOrderBinaryTreeIterator implements Iterator<T> {

		Stack<Pair<BinaryTreeNode, Boolean>> stack = new LinkedList<>();
		protected final int mods = serial;
		
		PostOrderBinaryTreeIterator() {
			this(root);
		}
		
		PostOrderBinaryTreeIterator(BinaryTreeNode node) {
			if(node != null)
				stack.push(new Pair<>(node, false));
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			ErrorChecks.assertThat(mods == serial, ConcurrentModificationException.class);
			
			Pair<BinaryTreeNode, Boolean> pair = stack.peek();
			
			if(pair.second) {
				stack.pop();
				return pair.first.value;
				
			} else {
				
				BinaryTreeNode node = pair.first;
				
				if(node.right != null) {
					stack.push(new Pair<>(node.right, false));
				}
				if(node.left != null) {
					stack.push(new Pair<>(node.left, false));
				}
				
				pair.second = true;
				
			}
			
			return next();
		}
		
	}
	
	/*https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram (Todd Davies answer)*/
	protected StringBuilder visualize(BinaryTreeNode node, StringBuilder prefix, boolean isTail, StringBuilder sb) {

	    if(node.right!=null) {
	        visualize(node.right, new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "\\—— " : "/—— ").append(node).append("\n");
	    if(node.left!=null) {
	        visualize(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb);
	    }
	    return sb;
	}
	

	@Override
	public String toString() {
	    return root != null 
	    		? visualize(root, new StringBuilder(), true, new StringBuilder()).toString()
	    		: "{}";
	}


}

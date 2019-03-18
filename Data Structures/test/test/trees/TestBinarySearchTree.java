package test.trees;

import org.junit.Before;

import datastructures.trees.BinarySearchTree;

public class TestBinarySearchTree {
	
	protected BinarySearchTree<Character> tree;
	
	protected void addData() {
		tree.addAll('F', 'B', 'F', 'G', 'A', 'D', 'C', 'A', 'G', 'E', 'I', 'H');		
	}
	
	@Before
	public void init() {
		tree = new BinarySearchTree<>();
	}
	
	
	
}

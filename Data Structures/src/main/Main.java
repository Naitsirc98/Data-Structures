package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import benchmarks.Benchmark;
import benchmarks.ListBenchmark;
import datastructures.Collection;
import datastructures.SortedCollection;
import datastructures.lists.ArrayList;
import datastructures.lists.List;
import datastructures.trees.BinaryTree;
import datastructures.trees.Heap;

public class Main {

	public static void main(String[] args) {
		
		BinaryTree<Integer> tree = new Heap<>();
		
		System.out.println(tree);
		
		List<Integer> list = new ArrayList<>();
		
		Random rand = new Random(System.nanoTime());
		
		for(int i = 0;i < 50;) {
			if(rand.nextFloat() < 0.5f) {
				int n = rand.nextInt(100);
				list.add(n);
				tree.add(n);
				i++;
			} else if(list.size() > 0) {
				tree.remove(list.get(rand.nextInt(list.size())));
			}
		}
		
		System.out.println(tree);
		
		/*System.out.println("In order");
		print(tree.inOrderIterator());
		System.out.println("Pre order");
		print(tree.preOrderIterator());
		System.out.println("Post order");
		print(tree.postOrderIterator());
		System.out.println("Breath");
		print(tree.breathIterator());*/
		
		// benchmark(new BinarySearchTree<>());
		
		/*listBenchmark(new ArrayList<>());
		listBenchmark(new LinkedList<>());
		listBenchmark(new DoublyLinkedList<>());
		benchmark(new ArraySet<>());
		benchmark(new LinkedSet<>()); 
		benchmark(new DoublyLinkedSet<>());
		benchmark(new SortedArrayList<>());
		benchmark(new SortedDoublyLinkedList<>());
		benchmark(new SortedLinkedList<>());*/
		
	}
	
	protected static <T> void shuffle(T[] array) {
        Random random = new Random();

        for (int i = 0;i < array.length;i++) {
            int index = i + random.nextInt(array.length - i);
            final T e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }
	
	private static void benchmark(Collection<Integer> collection) {
		Benchmark b = new Benchmark(collection);
		
		System.out.println("\n** "+b.getName()+" **");
		System.out.println(">> Testing "+ Benchmark.ITERATIONS 
				+ " sets of " + Benchmark.SAMPLE_SIZE + " random integers\n");
		
		b.addBenchmark();
		b.removeBenchmark();
		b.containsSuccessfulBenchmark();
		b.containsFailBenchmark();
		
		System.out.println("\n**---**\n");
		
	}
	
	private static void listBenchmark(List<Integer> list) {
		ListBenchmark b = new ListBenchmark(list);
		
		System.out.println("\n** "+b.getName()+" **");
		System.out.println(">> Testing "+ Benchmark.ITERATIONS 
				+ " sets of " + Benchmark.SAMPLE_SIZE + " random integers\n");
		
		b.addBenchmark();
		b.insertBenchmark();
		b.removeBenchmark();
		b.removeAtBenchmark();
		b.containsSuccessfulBenchmark();
		b.containsFailBenchmark();
		
		System.out.println("\n**---**\n");
		
	}
	
	public static <T> void print(Iterator<T> it) {
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}

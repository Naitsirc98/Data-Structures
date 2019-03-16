package main;

import benchmarks.Benchmark;
import benchmarks.ListBenchmark;
import datastructures.Collection;
import datastructures.lists.ArrayList;
import datastructures.lists.DoublyLinkedList;
import datastructures.lists.LinkedList;
import datastructures.lists.List;
import datastructures.lists.sorted.SortedArrayList;
import datastructures.lists.sorted.SortedDoublyLinkedList;
import datastructures.lists.sorted.SortedLinkedList;
import datastructures.sets.ArraySet;
import datastructures.sets.Set;

public class Main {

	public static void main(String[] args) {
		
		Set<Integer> set = new ArraySet<>(1,2,3,4,5,2,4,1,5);
		
		for(Integer i : set) {
			System.out.println(i);
		}
		
		listBenchmark(new ArrayList<>());
		listBenchmark(new LinkedList<>());
		listBenchmark(new DoublyLinkedList<>());
		benchmark(new SortedArrayList<>());
		benchmark(new SortedLinkedList<>());
		benchmark(new SortedDoublyLinkedList<>());
		
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

}

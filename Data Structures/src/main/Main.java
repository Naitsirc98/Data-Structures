package main;

import benchmarks.Benchmark;
import datastructures.Collection;
import datastructures.linear.ArrayList;
import datastructures.linear.DoublyLinkedList;
import datastructures.linear.LinkedList;
import datastructures.linear.sorted.SortedArrayList;

public class Main {

	public static void main(String[] args) {
		
		benchmark(new ArrayList<>());
		benchmark(new LinkedList<>());
		benchmark(new DoublyLinkedList<>());
		benchmark(new SortedArrayList<>());
		
	}
	
	private static void benchmark(Collection<Integer> collection) {
		Benchmark b = new Benchmark(collection);
		
		System.out.println("\n** "+b.getName()+"**\n");
		
		b.addBenchmark();
		b.removeBenchmark();
		b.containsSuccessfulBenchmark();
		b.containsFailBenchmark();
		
		System.out.println("\n**                **\n");
		
	}

}

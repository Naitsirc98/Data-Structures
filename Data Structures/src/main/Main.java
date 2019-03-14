package main;

import datastructures.linear.ArrayList;
import datastructures.linear.DoublyLinkedList;
import datastructures.linear.LinkedList;
import datastructures.linear.Queue;
import datastructures.linear.Stack;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("----- Testing Stack -----\n");
		
		System.out.println("** ArrayList **");
		benchmarkStack(new ArrayList<>());
		System.out.println("** DoublyLinkedList **");
		benchmarkStack(new DoublyLinkedList<>());
		
		System.out.println("\n---- Testing Queue -----\n");

		System.out.println("** DoublyLinkedList **");
		benchmarkQueue(new DoublyLinkedList<>());
		
	}
	
	private static void benchmarkStack(Stack<Integer> stack) {
		
		long start;
		
		System.out.println("testing push...");
		
		start = System.currentTimeMillis();
		
		for(int i = 0;i < 100000;i++) {
			stack.push(i);
		}
		
		System.out.println("Finished in " + (System.currentTimeMillis()-start)+" ms");
		
		System.out.println("testing pop...");
		
		start = System.currentTimeMillis();
		
		for(int i = 0;i < 100000;i++) {
			stack.pop();
		}
		
		System.out.println("Finished in " + (System.currentTimeMillis()-start)+" ms");
		
	}
	
	private static void benchmarkQueue(Queue<Integer> queue) {
		
		long start;
		
		System.out.println("testing enqueue...");
		
		start = System.currentTimeMillis();
		
		for(int i = 0;i < 100000;i++) {
			queue.enqueue(i);
		}
		
		System.out.println("Finished in " + (System.currentTimeMillis()-start)+" ms");
		
		System.out.println("testing poll...");
		
		start = System.currentTimeMillis();
		
		for(int i = 0;i < 100000;i++) {
			queue.poll();
		}
		
		System.out.println("Finished in " + (System.currentTimeMillis()-start)+" ms");
		
	}

}

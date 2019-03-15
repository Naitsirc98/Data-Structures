package benchmarks;

import java.util.Random;

import datastructures.lists.List;
import datastructures.util.Algorithms;
import datastructures.util.ErrorChecks;

public class ListBenchmark extends Benchmark {

	protected static int[] insertIndices;
	
	static {

		insertIndices = new int[DATA_SIZE];
		
		final Random rand = new Random(System.nanoTime());
		
		for(int i = 0;i < DATA_SIZE;i++) {
			insertIndices[i] = rand.nextInt(i+1);
		}
		
	}
	
	protected static int[] removeAtIndices;
	
	static {

		removeAtIndices = new int[DATA_SIZE];
		
		final Random rand = new Random(System.nanoTime());
		
		int j = 0;
		for(int i = DATA_SIZE-1;i >= 0;i--) {
			removeAtIndices[j++] = rand.nextInt(i+1);
		}
		
	}
	
	public ListBenchmark(List<Integer> list) {
		super(list);
	}

	public double[] insertBenchmark() {
		
		List<Integer> list = (List<Integer>) collection;
		
		list.clear();
		
		System.out.println("Benchmark(" + name + ") -> insert");
		
		ErrorChecks.assertThat(list.isEmpty(), "List must be empty for this benchmark");
		
		double[] results = new double[ITERATIONS];
		
		for(int i = 0;i < ITERATIONS;i++) {
			
			final double start = (double) System.nanoTime();
			
			for(int j = i*SAMPLE_SIZE;j < SAMPLE_SIZE*(i+1);j++) {
				list.insert(insertIndices[j], get(j));
			}
			
			results[i] = (((double) System.nanoTime()) - start) * NANO_TO_MILLIS;
		}
		
		System.out.println("Time average: "+Algorithms.average(results) + " ms");
		
		return results;
		
	}
	
	public double[] removeAtBenchmark() {
		
		List<Integer> list = (List<Integer>) collection;
		
		list.clear();
		
		addData();
		
		System.out.println("Benchmark(" + name + ") -> removeAt");
		
		ErrorChecks.assertThat(!list.isEmpty(), "List cannot be empty for this benchmark");
		
		double[] results = new double[ITERATIONS];
		
		for(int i = 0;i < ITERATIONS;i++) {
			
			final double start = (double) System.nanoTime();
			
			for(int j = i*SAMPLE_SIZE;j < SAMPLE_SIZE*(i+1);j++) {
				list.removeAt(removeAtIndices[j]);
			}
			
			results[i] = (((double) System.nanoTime()) - start) * NANO_TO_MILLIS;
		}
		
		System.out.println("Time average: "+Algorithms.average(results) + " ms");
		
		return results;
		
	}

}

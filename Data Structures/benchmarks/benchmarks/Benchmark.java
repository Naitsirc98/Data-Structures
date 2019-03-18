package benchmarks;

import java.util.Random;

import datastructures.Collection;
import datastructures.util.Algorithms;
import datastructures.util.ErrorChecks;

public class Benchmark {
	
	public static final double NANO_TO_MILLIS = 1.0 / 1000000.0;
	public static final int DATA_SIZE = 100000;
	public static final int ITERATIONS = 10;
	public static final int SAMPLE_SIZE = DATA_SIZE / ITERATIONS;
	
	protected final String name;
	protected Collection<Integer> collection;
	protected Integer[] data;
	
	{
		data = new Integer[DATA_SIZE];
		
		for(int i = 0;i < DATA_SIZE;i++) {
			data[i] = i;
		}
		
		shuffle(data);
	}
	
	protected static int[] indices;
	
	static {
		
		indices = new int[DATA_SIZE];
		
		for(int i = 0;i < DATA_SIZE;i++) {
			indices[i] = i;
		}
		
		shuffle(indices);
	}
	
	public Benchmark(Collection<Integer> collection) {
		ErrorChecks.assertNotNull(collection);
		this.collection = collection;
		name = collection.getClass().getSimpleName();

	}

	protected static <T> void shuffle(T[] array) {
        Random random = new Random();

        for (int i = 0;i < DATA_SIZE;i++) {
            int index = i + random.nextInt(DATA_SIZE - i);
            final T e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }
	
	protected static void shuffle(int[] array) {
        Random random = new Random();

        for (int i = 0;i < DATA_SIZE;i++) {
            int index = i + random.nextInt(DATA_SIZE - i);
            final int e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }
	
	protected void addData() {
		
		collection.clear();
		
		for(Integer i : data) 
			collection.add(i);
		
	}
	
	protected Integer get(int index) {
		return data[indices[index]];
	}
	
	public double[] containsSuccessfulBenchmark() {
		
		collection.clear();
		
		addData();
		
		System.out.println("Benchmark(" + name + ") -> contains (success)");
		
		ErrorChecks.assertThat(!collection.isEmpty(), "Collection cannot be empty for this benchmark");
		
		double[] results = new double[ITERATIONS];
		
		for(int i = 0;i < ITERATIONS;i++) {
			
			final double start = (double) System.nanoTime();
			
			for(int j = i*SAMPLE_SIZE;j < SAMPLE_SIZE*(i+1);j++) {
				collection.contains(get(j));
			}
			
			results[i] = (((double) System.nanoTime()) - start) * NANO_TO_MILLIS;
		}
		
		System.out.println("Time average: "+Algorithms.average(results) + " ms");
		
		return results;
	}
	
	public double[] containsFailBenchmark() {
		
		collection.clear();
		
		addData();
		
		System.out.println("Benchmark(" + name + ") -> contains (fail)");
		
		ErrorChecks.assertThat(!collection.isEmpty(), "Collection cannot be empty for this benchmark");
		
		double[] results = new double[ITERATIONS];
		
		for(int i = 0;i < ITERATIONS;i++) {
			
			final double start = (double) System.nanoTime();
			
			for(int j = i*SAMPLE_SIZE;j < SAMPLE_SIZE*(i+1);j++) {
				collection.contains(-get(j));
			}
			
			results[i] = (((double) System.nanoTime()) - start) * NANO_TO_MILLIS;
		}
		
		System.out.println("Time average: "+Algorithms.average(results) + " ms");
		
		return results;
	}
	
	public double[] addBenchmark() {
		
		collection.clear();
		
		System.out.println("Benchmark(" + name + ") -> add");
		
		ErrorChecks.assertThat(collection.isEmpty(), "Collection must be empty for this benchmark");
		
		double[] results = new double[ITERATIONS];
		
		for(int i = 0;i < ITERATIONS;i++) {
			
			final double start = (double) System.nanoTime();
			
			for(int j = i*SAMPLE_SIZE;j < SAMPLE_SIZE*(i+1);j++) {
				collection.add(get(j));
			}
			
			results[i] = (((double) System.nanoTime()) - start) * NANO_TO_MILLIS;
		}
		
		System.out.println("Time average: "+Algorithms.average(results) + " ms");
		
		return results;
	}
	
	public double[] removeBenchmark() {
		
		collection.clear();
		
		addData();
		
		System.out.println("Benchmark(" + name + ") -> remove");
		
		ErrorChecks.assertThat(!collection.isEmpty(), "Collection must be empty for this benchmark");
		
		double[] results = new double[ITERATIONS];
		
		for(int i = 0;i < ITERATIONS;i++) {
			
			final double start = (double) System.nanoTime();
			
			for(int j = i*SAMPLE_SIZE;j < SAMPLE_SIZE*(i+1);j++) {
				collection.remove(get(j));
			}
			
			results[i] = (((double) System.nanoTime()) - start) * NANO_TO_MILLIS;
		}
		
		ErrorChecks.assertThat(collection.isEmpty(), "Collection should be empty at this time");
		
		System.out.println("Time average: "+Algorithms.average(results) + " ms");
		
		return results;
	}

	public String getName() {
		return name;
	}

}

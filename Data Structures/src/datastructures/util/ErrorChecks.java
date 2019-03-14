package datastructures.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public final class ErrorChecks {

	private ErrorChecks() {}

	public static void assertThat(boolean condition) {
		assertThat(condition, "Condition was false");
	}

	public static void assertThat(boolean condition, String message) {

		if(!condition) {
			throw new AssertionError(message);
		}

	}
	
	public static void assertThat(boolean condition, Class<? extends RuntimeException> clazz) {
		
		if(!condition) {
			
			RuntimeException e = null;
			
			try {
				e = (RuntimeException) clazz.getConstructors()[0].newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException ex) {
				ex.printStackTrace();
			}
			
			throw e;
		}
		
	}
	
	public static void assertNotNull(Object obj) {
		
		if(obj == null) 
			throw new NullPointerException();
		
	}
	
	public static void indexCheck(int index, int min, int max) {

		if(index < min) {
			throw new IndexOutOfBoundsException("Index("+index+") < min("+min+")");
		} else if(index >= max) {
			throw new IndexOutOfBoundsException("Index("+index+") >= max("+max+")");
		}

	}

}

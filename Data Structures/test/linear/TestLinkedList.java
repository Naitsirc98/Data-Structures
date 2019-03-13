package linear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import datastructures.linear.LinkedList;
import datastructures.linear.List;

public class TestLinkedList extends TestList {

	@Override
	protected List<Integer> getList() {
		return new LinkedList<>();
	}
	
}

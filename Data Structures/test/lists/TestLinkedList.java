package lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import datastructures.lists.LinkedList;
import datastructures.lists.List;

public class TestLinkedList extends TestList {

	@Override
	protected List<Integer> getList() {
		return new LinkedList<>();
	}
	
}

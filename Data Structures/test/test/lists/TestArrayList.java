package test.lists;

import datastructures.lists.ArrayList;
import datastructures.lists.List;

public class TestArrayList extends TestList {

	@Override
	protected List<Integer> getList() {
		return new ArrayList<>();
	}
}

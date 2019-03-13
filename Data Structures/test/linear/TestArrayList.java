package linear;

import datastructures.linear.ArrayList;
import datastructures.linear.List;

public class TestArrayList extends TestList {

	@Override
	protected List<Integer> getList() {
		return new ArrayList<>();
	}
}

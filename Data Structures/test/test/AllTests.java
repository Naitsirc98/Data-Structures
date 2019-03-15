package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import lists.TestArrayList;
import lists.TestDoublyLinkedList;
import lists.TestLinkedList;
import lists.TestQueueDoublyLinkedList;
import lists.TestStackArrayList;
import lists.TestStackDoublyLinkedList;


@RunWith(Suite.class)
@SuiteClasses({
	TestArrayList.class, 
	TestLinkedList.class,
	TestDoublyLinkedList.class,
	
	TestStackArrayList.class,
	TestStackDoublyLinkedList.class,
	
	TestQueueDoublyLinkedList.class
})
public class AllTests {

}

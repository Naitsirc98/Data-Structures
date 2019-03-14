package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import linear.TestArrayList;
import linear.TestDoublyLinkedList;
import linear.TestLinkedList;
import linear.TestQueueDoublyLinkedList;
import linear.TestStackArrayList;
import linear.TestStackDoublyLinkedList;


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

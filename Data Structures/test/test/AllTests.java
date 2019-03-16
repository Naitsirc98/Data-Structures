package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.lists.TestArrayList;
import test.lists.TestDoublyLinkedList;
import test.lists.TestLinkedList;
import test.lists.sorted.TestSortedArrayList;
import test.lists.sorted.TestSortedDoublyLinkedList;
import test.lists.sorted.TestSortedLinkedList;
import test.restrictive.TestQueueArrayList;
import test.restrictive.TestQueueDoublyLinkedList;
import test.restrictive.TestQueueLinkedList;
import test.restrictive.TestStackArrayList;
import test.restrictive.TestStackDoublyLinkedList;
import test.restrictive.TestStackLinkedList;


@RunWith(Suite.class)
@SuiteClasses({
	TestArrayList.class, 
	TestLinkedList.class,
	TestDoublyLinkedList.class,
	
	TestStackArrayList.class,
	TestStackLinkedList.class,
	TestStackDoublyLinkedList.class,
	
	TestQueueArrayList.class,
	TestQueueLinkedList.class,
	TestQueueDoublyLinkedList.class,
	
	TestSortedArrayList.class,
	TestSortedLinkedList.class,
	TestSortedDoublyLinkedList.class
})
public class AllTests {

}

package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import linear.TestArrayList;
import linear.TestDoublyLinkedList;
import linear.TestLinkedList;


@RunWith(Suite.class)
@SuiteClasses({
	TestArrayList.class, 
	TestLinkedList.class,
	TestDoublyLinkedList.class
})
public class AllTests {

}

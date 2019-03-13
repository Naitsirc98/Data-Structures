package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import linear.TestArrayList;
import linear.TestLinkedList;


@RunWith(Suite.class)
@SuiteClasses({
	TestArrayList.class, 
	TestLinkedList.class
})
public class AllTests {

}

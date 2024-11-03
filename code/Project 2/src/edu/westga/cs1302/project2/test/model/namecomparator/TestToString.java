package edu.westga.cs1302.project2.test.model.namecomparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.NameComparator;

class TestToString {

	@Test
	void test() {
		NameComparator testComparator = new NameComparator();
		
		String result = testComparator.toString();
		
		assertEquals("Name", result, "checking result of the NameComparator::toString()");
	}

}

package edu.westga.cs1302.project2.test.model.typecomparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.TypeComparator;

class TestToString {

	@Test
	void test() {
		TypeComparator testComparator = new TypeComparator();
		
		String result = testComparator.toString();
		
		assertEquals("Type", result, "checking result of the TypeComparator::toString()");
	}

}

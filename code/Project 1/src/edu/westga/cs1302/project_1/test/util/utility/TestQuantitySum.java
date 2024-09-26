package edu.westga.cs1302.project_1.test.util.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project_1.util.Utility;

class TestQuantitySum {

	@Test
	void testGivenNull() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.quantitySum(null);});
	}

}

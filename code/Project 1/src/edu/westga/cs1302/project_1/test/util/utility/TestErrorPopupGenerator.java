package edu.westga.cs1302.project_1.test.util.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project_1.util.Utility;


class TestErrorPopupGenerator {

	@Test
	void testGivenNulls() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.errorPopupGenerator(null, null, null);});
	}
	
	@Test
	void testNullUnable() {
		IllegalArgumentException error = new IllegalArgumentException("title");
		assertThrows(IllegalArgumentException.class, ()->{Utility.errorPopupGenerator(null, "recommend", error);});
	}
	
	@Test
	void testNullRecommendation() {
		IllegalArgumentException error = new IllegalArgumentException("title");
		assertThrows(IllegalArgumentException.class, ()->{Utility.errorPopupGenerator("unable", null, error);});
	}
	
	@Test
	void testNullError() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.errorPopupGenerator("unable", "recommend", null);});
	}
	
}


package edu.westga.cs1302.project_1.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project_1.model.Food;

class TestIncrementQuantity {

	@Test 
	void testOneBelowMaximumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(2147483645);
		testFood.incrementQuantity();
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(2147483646, testFood.getQuantity());
	}
	
	@Test
	void testAtMaximumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(2147483646);
		testFood.incrementQuantity();
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(2147483647, testFood.getQuantity());
	}
	
	@Test
	void testOneAboveMaximumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(2147483647);
		assertThrows(IllegalStateException.class, ()->{testFood.incrementQuantity();});
	}
	
	@Test
	void testValidAmount() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(123);
		testFood.incrementQuantity();
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(124, testFood.getQuantity());
	}

}

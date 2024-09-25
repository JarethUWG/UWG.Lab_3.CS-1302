package edu.westga.cs1302.project_1.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project_1.model.Food;

class TestDecrementQuantity {

	@Test 
	void testOneBelowMinimumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(0);
		assertThrows(IllegalStateException.class, ()->{testFood.decrementQuantity();});
	}
	
	@Test
	void testAtMinimumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(1);
		testFood.decrementQuantity();
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(0, testFood.getQuantity());
	}
	
	@Test
	void testOneAboveMinimumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(2);
		testFood.decrementQuantity();
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(1, testFood.getQuantity());
	}
	
	@Test
	void testValidAmount() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(123);
		testFood.decrementQuantity();
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(122, testFood.getQuantity());
	}

}

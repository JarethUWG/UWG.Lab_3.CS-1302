package edu.westga.cs1302.project_1.test.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project_1.model.Food;

public class TestSetNextQuantity {
	
	@Test
	void testOneBelowMinimumBoundary() {
		Food testFood = new Food("name", "type");
		assertThrows(IllegalArgumentException.class, ()->{testFood.setNextQuantity(-1);});
	}
	
	@Test
	void testAtMinimumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(0);
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(0, testFood.getQuantity());
	}
	
	@Test
	void testOneAboveMinimumBoundary() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(1);
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(1, testFood.getQuantity());
	}
	
	@Test
	void testValidAmount() {
		Food testFood = new Food("name", "type");
		testFood.setNextQuantity(123);
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(123, testFood.getQuantity());
	}

}

package edu.westga.cs1302.project_1.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project_1.model.Food;

public class TestConstructor {
	
	@Test
	void testNullNameAndType() {
		assertThrows(IllegalArgumentException.class, ()->{new Food(null, null);});
	}
	
	@Test
	void testNullType() {
		assertThrows(IllegalArgumentException.class, ()->{new Food("name", null);});
	}
	
	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, ()->{new Food(null, "type");});
	}
	
	@Test
	void testBlankName() {
		assertThrows(IllegalArgumentException.class, ()->{new Food(" ", "type");});
	}
	
	@Test
	void testValidNameAndType() {
		Food testFood = new Food("name", "type");
		assertEquals("name", testFood.getName());
		assertEquals("type", testFood.getType());
		assertEquals(0, testFood.getQuantity());
	}
	
}

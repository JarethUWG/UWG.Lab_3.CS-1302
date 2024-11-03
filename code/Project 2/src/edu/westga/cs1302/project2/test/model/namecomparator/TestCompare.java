package edu.westga.cs1302.project2.test.model.namecomparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;

import edu.westga.cs1302.project2.model.NameComparator;

class TestCompare {

	@Test
	void testNullFirstIngredient() {
		Ingredient testIngredient2 = new Ingredient("B", "B");
		NameComparator testComparator = new NameComparator();
		assertThrows(IllegalArgumentException.class, ()->{testComparator.compare(null, testIngredient2);});
	}
	
	@Test
	void testNullSecondIngredient() {
		Ingredient testIngredient1 = new Ingredient("A", "A");
		NameComparator testComparator = new NameComparator();
		assertThrows(IllegalArgumentException.class, ()->{testComparator.compare(testIngredient1, null);});
	}
	
	@Test
	void testSameTypes() {
		Ingredient testIngredient1 = new Ingredient("C", "A");
		Ingredient testIngredient2 = new Ingredient("C", "B");
		NameComparator testComparator = new NameComparator();
		assertEquals(0, testComparator.compare(testIngredient1, testIngredient2), "checking neutral output");
	}
	
	@Test
	void testFirstGreater() {
		Ingredient testIngredient1 = new Ingredient("D", "A");
		Ingredient testIngredient2 = new Ingredient("B", "B");
		NameComparator testComparator = new NameComparator();
		assertEquals(1, testComparator.compare(testIngredient1, testIngredient2), "checking positive output");
	}
	
	@Test
	void testSecondGreater() {
		Ingredient testIngredient1 = new Ingredient("B", "A");
		Ingredient testIngredient2 = new Ingredient("D", "B");
		NameComparator testComparator = new NameComparator();
		assertEquals(-1, testComparator.compare(testIngredient1, testIngredient2), "checking negative output");
	}
	
}

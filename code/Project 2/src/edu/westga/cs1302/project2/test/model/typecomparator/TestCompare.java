package edu.westga.cs1302.project2.test.model.typecomparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;

import edu.westga.cs1302.project2.model.TypeComparator;

class TestCompare {

	@Test
	void testNullFirstIngredient() {
		Ingredient testIngredient2 = new Ingredient("B", "B");
		TypeComparator testComparator = new TypeComparator();
		assertThrows(IllegalArgumentException.class, ()->{testComparator.compare(null, testIngredient2);});
	}
	
	@Test
	void testNullSecondIngredient() {
		Ingredient testIngredient1 = new Ingredient("A", "A");
		TypeComparator testComparator = new TypeComparator();
		assertThrows(IllegalArgumentException.class, ()->{testComparator.compare(testIngredient1, null);});
	}
	
	@Test
	void testSameTypes() {
		Ingredient testIngredient1 = new Ingredient("A", "C");
		Ingredient testIngredient2 = new Ingredient("B", "C");
		TypeComparator testComparator = new TypeComparator();
		assertEquals(0, testComparator.compare(testIngredient1, testIngredient2), "checking neutral output");
	}
	
	@Test
	void testFirstGreater() {
		Ingredient testIngredient1 = new Ingredient("A", "D");
		Ingredient testIngredient2 = new Ingredient("B", "B");
		TypeComparator testComparator = new TypeComparator();
		assertEquals(1, testComparator.compare(testIngredient1, testIngredient2), "checking positive output");
	}
	
	@Test
	void testSecondGreater() {
		Ingredient testIngredient1 = new Ingredient("A", "B");
		Ingredient testIngredient2 = new Ingredient("B", "D");
		TypeComparator testComparator = new TypeComparator();
		assertEquals(-1, testComparator.compare(testIngredient1, testIngredient2), "checking negative output");
	}

}

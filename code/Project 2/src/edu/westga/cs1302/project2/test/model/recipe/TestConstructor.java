package edu.westga.cs1302.project2.test.model.recipe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Recipe;

import edu.westga.cs1302.project2.model.Ingredient;

class TestConstructor {

	@Test
	void testNullName() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		testIngredients.add(testIngredient1);
		assertThrows(IllegalArgumentException.class, ()->{new Recipe(null, testIngredients);});
	}

	@Test
	void testEmptyName() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		testIngredients.add(testIngredient1);
		assertThrows(IllegalArgumentException.class, ()->{new Recipe("", testIngredients);});
	}
	
	@Test
	void testNullIngredients() {
		assertThrows(IllegalArgumentException.class, ()->{new Recipe("testName", null);});
	}

	@Test
	void testEmptyIngredients() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		assertThrows(IllegalArgumentException.class, ()->{new Recipe("testName", testIngredients);});
	}
	
	@Test
	void testValidNameAndType() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		testIngredients.add(testIngredient1);
		Recipe result = new Recipe("testName", testIngredients);
		assertEquals("testName", result.getName(), "checking the name of the recipe");
		assertEquals("testBeef", result.getIngredients().get(0).getName(), "checking the name of ingredient in the recipe");
		assertEquals("Meat", result.getIngredients().get(0).getType(), "checking the type of ingredient in the recipe");
	}
}

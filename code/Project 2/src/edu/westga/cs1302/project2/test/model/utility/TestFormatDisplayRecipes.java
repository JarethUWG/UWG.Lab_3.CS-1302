package edu.westga.cs1302.project2.test.model.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.Utility;

class TestFormatDisplayRecipes {
	
	@Test
	void testNullList() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.formatDisplayRecipes(null);});
	}
	
	@Test
	void testEmptyList() {
		ArrayList<Recipe> testRecipes = new ArrayList<Recipe>();
		assertEquals("", Utility.formatDisplayRecipes(testRecipes));
	}
	
	@Test
	void testValidRecipes() {
		ArrayList<Recipe> testRecipes = new ArrayList<Recipe>();
		ArrayList<Ingredient> testIngredients1 = new ArrayList<Ingredient>();
		ArrayList<Ingredient> testIngredients2 = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		Ingredient testIngredient2 = new Ingredient("testPotato", "Vegetable");
		testIngredients1.add(testIngredient1);
		testIngredients2.add(testIngredient1);
		testIngredients2.add(testIngredient2);
		Recipe recipe1 = new Recipe("testName1", testIngredients1);
		Recipe recipe2 = new Recipe("testName2", testIngredients2);
		testRecipes.add(recipe1);
		testRecipes.add(recipe2);
		String nL = System.lineSeparator();
		String expectedOutput = "testName1" + nL + "testBeef-Meat" + nL + nL +  "testName2" + nL  + "testBeef-Meat, " + "testPotato-Vegetable";
		assertEquals(expectedOutput, Utility.formatDisplayRecipes(testRecipes));
	}

}

package edu.westga.cs1302.project2.test.model.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Utility;

class TestFormatRecipe {

	@Test
	void testNullName() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		testIngredients.add(testIngredient1);
		assertThrows(IllegalArgumentException.class, ()->{ Utility.formatRecipe(null, testIngredients);});
	}

	@Test
	void testEmptyName() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		testIngredients.add(testIngredient1);
		assertThrows(IllegalArgumentException.class, ()->{ Utility.formatRecipe("", testIngredients);});
	}
	
	@Test
	void testNullIngredients() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.formatRecipe("testName", null);});
	}

	@Test
	void testEmptyIngredients() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		assertThrows(IllegalArgumentException.class, ()->{Utility.formatRecipe("testName", testIngredients);});
	}
	
	@Test
	void testValidOneIngredient() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		testIngredients.add(testIngredient1);
		String nextLine = System.lineSeparator();
		String excpectedOutput = "testName" + nextLine + "testBeef" + "," + "Meat";
		assertEquals(excpectedOutput, Utility.formatRecipe("testName", testIngredients));
	}
	
	@Test
	void testValidMultipleIngredients() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		Ingredient testIngredient2 = new Ingredient("testPotato", "Vegetable");
		testIngredients.add(testIngredient1);
		testIngredients.add(testIngredient2);
		String nextLine = System.lineSeparator();
		String excpectedOutput = "testName" + nextLine + "testBeef" + "," + "Meat" + "," + "testPotato" + "," + "Vegetable";
		assertEquals(excpectedOutput, Utility.formatRecipe("testName", testIngredients));
	}

}

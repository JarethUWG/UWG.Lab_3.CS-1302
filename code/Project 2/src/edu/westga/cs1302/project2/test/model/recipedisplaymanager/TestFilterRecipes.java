package edu.westga.cs1302.project2.test.model.recipedisplaymanager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeDisplayManager;

class TestFilterRecipes {
	
	@Test
	void testNullRecipes() {
		Ingredient testIngredient1 = new Ingredient("testBeef", "Meat");
		assertThrows(IllegalArgumentException.class, ()->{RecipeDisplayManager.filterRecipes(null, testIngredient1);});
	}
	
	@Test
	void testNullIngredient() {
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
		assertThrows(IllegalArgumentException.class, ()->{RecipeDisplayManager.filterRecipes(testRecipes, null);});
	}
	
	@Test
	void testValidRecipesAndIngredient() {
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
		assertEquals("testName2", RecipeDisplayManager.filterRecipes(testRecipes, testIngredient2).get(0).getName());
		assertEquals("testBeef", RecipeDisplayManager.filterRecipes(testRecipes, testIngredient2).get(0).getIngredients().get(0).getName());
		assertEquals("Meat", RecipeDisplayManager.filterRecipes(testRecipes, testIngredient2).get(0).getIngredients().get(0).getType());
		assertEquals("testPotato", RecipeDisplayManager.filterRecipes(testRecipes, testIngredient2).get(0).getIngredients().get(1).getName());
		assertEquals("Vegetable", RecipeDisplayManager.filterRecipes(testRecipes, testIngredient2).get(0).getIngredients().get(1).getType());
	}

}

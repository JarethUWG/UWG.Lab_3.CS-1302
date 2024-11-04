package edu.westga.cs1302.project2.test.model.recipedisplaymanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeDisplayManager;


class TestLoadRecipes {
	private static final String DATA_FILE = "recipes.txt";
	
	@Test
	void testNothingToLoad() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		ArrayList<Recipe> expected = new ArrayList<Recipe>();
		assertEquals(expected, RecipeDisplayManager.loadRecipes());
		fileRemover.delete();
	}
	
	@Test
	void testBadRecipeNameData() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("" + System.lineSeparator() + "Valid,Ingredient");
			}
		assertThrows(IOException.class, ()->{RecipeDisplayManager.loadRecipes();});
		fileRemover.delete();
	}
	
	@Test
	void testBadRecipeIngredientData() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("ValidName" + System.lineSeparator() + "Invalid");
			}
		assertThrows(IOException.class, ()->{RecipeDisplayManager.loadRecipes();});
		fileRemover.delete();
	}
	
	@Test
	void testSingleValidRecipe() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("ValidName" + System.lineSeparator() + "Valid,Ingredient");
			}
		assertEquals("ValidName", RecipeDisplayManager.loadRecipes().get(0).getName());
		assertEquals("Valid", RecipeDisplayManager.loadRecipes().get(0).getIngredients().get(0).getName());
		assertEquals("Ingredient", RecipeDisplayManager.loadRecipes().get(0).getIngredients().get(0).getType());
		fileRemover.delete();
	}
	
	@Test
	void testMultipleValidRecipes() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("ValidName" + System.lineSeparator() + "Valid,Ingredient" + System.lineSeparator() + "ValidName2" + System.lineSeparator() + "This,Is,Also,Valid");
			}
		assertEquals("ValidName", RecipeDisplayManager.loadRecipes().get(0).getName());
		assertEquals("Valid", RecipeDisplayManager.loadRecipes().get(0).getIngredients().get(0).getName());
		assertEquals("Ingredient", RecipeDisplayManager.loadRecipes().get(0).getIngredients().get(0).getType());
		assertEquals("ValidName2", RecipeDisplayManager.loadRecipes().get(1).getName());
		assertEquals("This", RecipeDisplayManager.loadRecipes().get(1).getIngredients().get(0).getName());
		assertEquals("Is", RecipeDisplayManager.loadRecipes().get(1).getIngredients().get(0).getType());
		assertEquals("Also", RecipeDisplayManager.loadRecipes().get(1).getIngredients().get(1).getName());
		assertEquals("Valid", RecipeDisplayManager.loadRecipes().get(1).getIngredients().get(1).getType());
		fileRemover.delete();
	}

}

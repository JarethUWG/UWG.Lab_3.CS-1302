package edu.westga.cs1302.project2.test.model.recipepersistencemanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project2.model.RecipePersistenceManager;

class TestSaveRecipeData {
	private static final String DATA_FILE = "recipes.txt";
	
	@Test
	void testNullRecipe() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		assertThrows(IllegalArgumentException.class, ()-> { RecipePersistenceManager.saveRecipeData(null);});
		fileRemover.delete();
	}
	
	@Test
	void testRepeatedNames() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		String nL = System.lineSeparator();
		String recipe1 = "testName1" + nL + "testBeef,Meat";
		String recipe2 = "testName1" + nL + "testBeef,Meat,testPotato,Vegetable";
		RecipePersistenceManager.saveRecipeData(recipe1);
		assertThrows(IllegalStateException.class, ()-> { RecipePersistenceManager.saveRecipeData(recipe2);});
		fileRemover.delete();
	}
	
	@Test
	void testNoSave() throws IOException {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		String nL = System.lineSeparator();
		String recipe1 = "testName1" + nL + "testBeef,Meat";
		RecipePersistenceManager.saveRecipeData(recipe1);
		String resultRecipeName1 = "";
		String resultIngredientName1 = "";
		String resultIngredientType1 = "";
		try (Scanner reader = new Scanner(testFile)) {
			for (int index = 0; reader.hasNextLine(); index++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				if (index == 0) {
					resultRecipeName1 = parts[0];
				}
				if (index == 1) {
					resultIngredientName1 = parts[0];
					resultIngredientType1 = parts[1];
				}
			}
		}
		assertEquals("testName1", resultRecipeName1);
		assertEquals("testBeef", resultIngredientName1);
		assertEquals("Meat", resultIngredientType1);
		fileRemover.delete();
	}
	
	@Test
	void testMultipleSaves() throws IOException  {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		String nL = System.lineSeparator();
		String recipe1 = "testName1" + nL + "testBeef,Meat";
		String recipe2 = "testName2" + nL + "testBeef,Meat,testPotato,Vegetable";
		RecipePersistenceManager.saveRecipeData(recipe1);
		RecipePersistenceManager.saveRecipeData(recipe2);
		String resultRecipeName1 = "";
		String resultIngredientName1 = "";
		String resultIngredientType1 = "";
		String resultRecipeName2 = "";
		String resultIngredientName2 = "";
		String resultIngredientType2 = "";
		String resultIngredientName3 = "";
		String resultIngredientType3 = "";
		try (Scanner reader = new Scanner(testFile)) {
			for (int index = 0; reader.hasNextLine(); index++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				if (index == 0) {
					resultRecipeName1 = parts[0];
				}
				if (index == 1) {
					resultIngredientName1 = parts[0];
					resultIngredientType1 = parts[1];
				}
				if (index == 2) {
					resultRecipeName2 = parts[0];
				}
				if (index == 3) {
					resultIngredientName2 = parts[0];
					resultIngredientType2 = parts[1];
					resultIngredientName3 = parts[2];
					resultIngredientType3 = parts[3];
				}
			}
		}
		assertEquals("testName1", resultRecipeName1);
		assertEquals("testBeef", resultIngredientName1);
		assertEquals("Meat", resultIngredientType1);
		assertEquals("testName2", resultRecipeName2);
		assertEquals("testBeef", resultIngredientName2);
		assertEquals("Meat", resultIngredientType2);
		assertEquals("testPotato", resultIngredientName3);
		assertEquals("Vegetable", resultIngredientType3);
		fileRemover.delete();
	}
	
}

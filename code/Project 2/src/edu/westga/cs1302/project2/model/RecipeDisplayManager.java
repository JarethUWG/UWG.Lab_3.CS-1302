package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Manager for the logic of loading and displaying recipes
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class RecipeDisplayManager {
	public static final String DATA_FILE = "recipes.txt";
	
	/** Loads the recipes from a file
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format as saveRecipeData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of recipes
	 * @throws IOException if formatting issues exist in accessed file
	 */
	public static List<Recipe> loadRecipes() throws IOException {
		File inputFile = new File(DATA_FILE);
		List<Recipe> recipes = new ArrayList<Recipe>();
		int currentIndex = 0;
		try (Scanner reader = new Scanner(inputFile)) {
			String currentName = "";
			Ingredient currentIngredient;
			for (int index = 0; reader.hasNextLine(); index++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				if (index % 2 == 0) {
					currentName = parts[0];
				} else {
					List<Ingredient> currentIgredients = new ArrayList<Ingredient>();
					for (int gatherer = 0; gatherer < parts.length; gatherer++) {
						String currentIngredientName = parts[gatherer];
						gatherer++;
						String currentIngredientType = parts[gatherer];
						currentIngredient = new Ingredient(currentIngredientName, currentIngredientType);
						currentIgredients.add(currentIngredient);
					}
					Recipe currentRecipe = new Recipe(currentName, currentIgredients);
					recipes.add(currentRecipe);
				}
				currentIndex++;
			} 
		} catch (IllegalArgumentException recipeDataError) {
			throw new IOException("Invalid Recipe information on line " + (currentIndex + 1));
		} catch (IndexOutOfBoundsException fileFormatError) {
			throw new IOException("Improper formatting of information on line " + (currentIndex + 1));
		} catch (FileNotFoundException noSaveError) {
			return recipes;
		}
		return recipes;
	}	
	
	/** Loads the recipes with a specified ingredient
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param ingredient the ingredient that is being searched for in the recipes
	 * 
	 * @return list of recipes containing ingredient
	 * @throws IOException if formatting issues exist in accessed file
	 */
	public static List<Recipe> filterRecipes(Ingredient ingredient) throws IOException {
		List<Recipe> filteredRecipes = new ArrayList<Recipe>();
		List<Recipe> allRecipes = RecipeDisplayManager.loadRecipes();
		for (Recipe currentRecipe: allRecipes) {
			boolean matchingIngredient = false;
			for (Ingredient currentIgredient: currentRecipe.getIngredients()) {
				if (currentIgredient.getName().equals(ingredient.getName()) && currentIgredient.getType().equals(ingredient.getType())) {
					matchingIngredient = true;
				}
			}
			if (matchingIngredient) {
				filteredRecipes.add(currentRecipe);
			}
		}
		return filteredRecipes;
	}

}

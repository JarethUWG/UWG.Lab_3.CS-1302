package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Supports saving of recipe data
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class RecipePersistenceManager {
	public static final String DATA_FILE = "recipes.txt";
	
	/** Save a recipe as a CSV
	 * 
	 * Writes all recipe data to DATA_FILE
	 * 
	 * @precondition recipe != null
	 * @postcondition none
	 * 
	 * @param recipe the recipe to save
	 * @throws IOException if any issues occur in writing
	 * @throws IllegalArgumentException if precondition is violated
	 * @throws IllegalStateException if a recipe sharing the name of inputed recipe already exists.
	 */
	public void saveRecipeData(Recipe recipe) throws IOException, IllegalArgumentException, IllegalStateException {
		if (recipe == null) {
			throw new IllegalArgumentException("Must provide a valid recipe");
		}
		boolean isSaveData = true;
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			File recipeFile = new File(DATA_FILE);
			try (Scanner reader = new Scanner(recipeFile)) {
				int iteration = 0;
				while (reader.hasNextLine()) {
					String currentLine = reader.nextLine();
					if (iteration % 2 == 0) {
						if (recipe.getName().equals(currentLine)) {
							throw new IllegalStateException("Recipes in file cannot share names");
						}
					}
					iteration++;
				}
			} catch (FileNotFoundException noSaveData) {
				writer.write(Utility.formatRecipe(recipe.getName(), recipe.getIngredients()) + System.lineSeparator());
				isSaveData = false;
			}
			if (isSaveData) {
			writer.write(Utility.formatRecipe(recipe.getName(), recipe.getIngredients()) + System.lineSeparator());
			}
		}
		
	}
}

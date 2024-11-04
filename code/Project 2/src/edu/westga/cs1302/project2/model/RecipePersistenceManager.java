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
	public static void saveRecipeData(String recipe) throws IOException, IllegalArgumentException, IllegalStateException {
		if (recipe == null) {
			throw new IllegalArgumentException("Must provide a valid recipe");
		}
		String oldData = "";
		File recipeFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(recipeFile)) {
			String[] nameIngredientSpliter = recipe.split(System.lineSeparator());
			int iteration = 0;
			while (reader.hasNextLine()) {
				String currentLine = reader.nextLine();
				if (iteration % 2 == 0) {
					if (nameIngredientSpliter[0].equals(currentLine)) {
						throw new IllegalStateException("Recipes in file cannot share names");
					}
				}
				oldData += currentLine + System.lineSeparator();
				iteration++;
			} 
		} catch (FileNotFoundException noSave) {
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write(oldData);
			writer.write(recipe + System.lineSeparator());
			}
		}
		
}


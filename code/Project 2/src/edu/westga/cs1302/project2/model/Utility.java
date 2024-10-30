package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * Holder for the static methods of model
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class Utility {
	
	/**
	 * Formats the toString for a recipe
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 * 				 ingredients != null && !ingredients.isEmpty()
	 * @param name the name of the recipe
	 * @param ingredients the ingredients for the recipe
	 * @return a formatted toString for a recipe
	 */
	public static String formatRecipe(String name, List<Ingredient> ingredients) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Must provide a name");
		}
		if (ingredients == null || ingredients.isEmpty()) {
			throw new IllegalArgumentException("Must provide a list of ingredients");
		} 
		String formattedString = name + System.lineSeparator();
		for (int index = 0; index < ingredients.size(); index++) {
			if (index == ingredients.size() - 1) {
				formattedString += ingredients.get(index).getName();
			} else {
				formattedString += ingredients.get(index).getName() + ",";
			}
		}
		return formattedString;
	}
}

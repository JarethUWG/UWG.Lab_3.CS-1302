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
	 * Formats the information of a recipe to be saved as a CSV
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 * 				 ingredients != null && !ingredients.isEmpty()
	 * @param name the name of the recipe
	 * @param ingredients the ingredients for the recipe
	 * @return a formatted string for a recipe
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
				formattedString += ingredients.get(index).getName() + "," + ingredients.get(index).getType();
			} else {
				formattedString += ingredients.get(index).getName() + "," + ingredients.get(index).getType() + ",";
			}
		}
		return formattedString;
	}
	
	/**
	 * Formats the information of a recipes to be displayed as a String
	 * 
	 * @precondition name != null 
	 * 
	 * @param recipes the recipes to be formatted
	 * @return a formatted string for a list of recipes
	 */
	public static String formatDisplayRecipes(List<Recipe> recipes) {
		if (recipes == null) {
			throw new IllegalArgumentException("Must provide a list of recipes");
		}
		String formattedString = "";
		for (int index = 0; index < recipes.size(); index++) {
			if (index == recipes.size() - 1) {
				formattedString += recipes.get(index).getName();
			} else {
				formattedString += recipes.get(index).getName() + System.lineSeparator();
			}
		}
		return formattedString;
	}
}

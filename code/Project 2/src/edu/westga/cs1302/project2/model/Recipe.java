package edu.westga.cs1302.project2.model;

import java.util.List;

/** Store information for a single Recipe
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class Recipe {
	private String name;
	private List<Ingredient> ingredients;
	
	/** Create a new recipe with the specified name and ingredients
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 * 				 ingredients != null && !ingredients.isEmpty()
	 * @postcondition getName() == name &&
	 * 				  getIngredients() == ingredients
	 * 
	 * @param name the name of the recipe
	 * @param ingredients the ingredients for the recipe
	 */
	public Recipe(String name, List<Ingredient> ingredients) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Must provide a name");
		}
		if (ingredients == null || ingredients.isEmpty()) {
			throw new IllegalArgumentException("Must provide a list of ingredients");
		} 
		this.name = name;
		this.ingredients = ingredients;
	}
	
	/**
	 * Gets the current value of name
	 * 
	 * @return the current value of name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the current value of ingredients 
	 * 
	 * @return the current value of ingredients 
	 */
	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}
	
	@Override
	public String toString() {
		return Utility.formatRecipe(this.name, this.ingredients);
	}
}

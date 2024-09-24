package edu.westga.cs1302.project_1.model;

/** Stores and manages information for a single food
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Food {
	private final String name;
	private final String type;
	private int quantity;
	
	/** Create a new food with the specified name and type
	 * 
	 * @precondition name != null && name.length() >= 1 && type != null
	 * @postcondition getName() == name && getType() == type && quantity == 0
	 * 
	 * @param name the name of the new food
	 * @param type the type of the new food
	 */
	public Food(String name, String type) {
		if (name == null) {
			throw new IllegalArgumentException("Name must be provided.");
		}
		if (name.length() < 1) {
			throw new IllegalArgumentException("Name must have at least 1 character.");
		}
		if (type == null) {
			throw new IllegalArgumentException("Type must be provided.");
		}
		this.name = name;
		this.type = type;
		this.quantity = 0;
	}
	
	/** Creates a formatted string containing the name and quantity of the food
	 * 
	 * @return a formatted string containing the name and quantity of the food
	 */
	public String toString() {
		String text = this.name + "-" + this.quantity;
		return text;
	}
	
	/** Returns the name of the food
	 * 
	 * @return the name of the food
	 */
	public String getName() {
		return this.name;
	}
	
	/** Returns the type of the food
	 * 
	 * @return the type of the food
	 */
	public String getType() {
		return this.type;
	}
	
	/** Returns the quantity of the food
	 * 
	 * @return the quantity of the food
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
}

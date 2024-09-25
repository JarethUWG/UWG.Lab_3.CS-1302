package edu.westga.cs1302.project_1.util;

import edu.westga.cs1302.project_1.model.Food;
import javafx.scene.control.ListView;

/**
 * Manages static methods
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class Utility {
	
	/**
	 * Sums up all quantities of all the foods in a list view
	 * 
	 * @param foodList the list view to sum
	 * @return sumFood the sum of all quantities of all foods in the list view
	 */
	public static int quantitySum(ListView<Food> foodList) {
		int sumFood = 0;
		for (Food currentFood: foodList.getItems()) {
			sumFood += currentFood.getQuantity();
		}
		return sumFood;
	}
}

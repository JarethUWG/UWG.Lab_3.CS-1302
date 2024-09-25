package edu.westga.cs1302.project_1.util;

import edu.westga.cs1302.project_1.model.Food;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

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
	
	/**
	 * Sums up all quantities of all the foods in a list view
	 * 
	 * @param unableMessage a message to be put before the error often use to state what can't be done
	 * @param recommendationMessage a message to be put after the error often used to suggest how to resolve the error
	 * @param exception the error which a general message is gotten from
	 */
	public static void errorPopupGenerator(String unableMessage, String recommendationMessage, Exception exception) {
		Alert errorPopup = new Alert(AlertType.ERROR);
		errorPopup.setContentText(unableMessage + exception.getMessage() + recommendationMessage);
		errorPopup.showAndWait();
	}
}

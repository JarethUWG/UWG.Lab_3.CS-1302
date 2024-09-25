package edu.westga.cs1302.project_1.view;

import edu.westga.cs1302.project_1.model.Food;
import edu.westga.cs1302.project_1.util.Utility;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.fxml.FXML;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	
	 	@FXML
	    private TextField name;

	    @FXML
	    private ListView<Food> pantry;

	    @FXML
	    private ComboBox<String> type;
	    
	    @FXML
	    private TextField nextQuantity;

	    @FXML
	    void addFood(ActionEvent event) {
	    	String foodName = this.name.getText();
	    	String foodType = this.type.getValue();
	    	try {
	    		Food food = new Food(foodName, foodType);
	        	this.pantry.getItems().add(food);
	    	} catch (IllegalArgumentException error) {
	    		if (this.name.getText() == null || this.name.getText().isBlank() && this.type.getValue() == null) {
	        		Utility.errorPopupGenerator("Unable to add food: ", " Please add a name and type for the food you wish to add.", error);
	    		} else if (this.name.getText() == null || this.name.getText().isBlank()) {
	        		Utility.errorPopupGenerator("Unable to add food: ", " Please add a name for the food you wish to add.", error);
	    		}  else if (this.type.getValue() == null) {
	        		Utility.errorPopupGenerator("Unable to add food: ", " Please add a type for the food you wish to add.", error);
	    		}
	    	}
	    }
	    	
	    @FXML
	    void removeFood(ActionEvent event) {
	    	Food badFood = this.pantry.getSelectionModel().getSelectedItem();
        	if (badFood != null) {
        	this.pantry.getItems().remove(badFood);
        	} else {
        		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("No food selected. Please select a food to remove.");
        		errorPopup.showAndWait();
        	}	
	    }
	    
	    @FXML
	    void countQuantities(ActionEvent event) {
	    	int quantitySum = Utility.quantitySum(this.pantry);
	    	if (quantitySum == 1) {
	    		Alert infoPopup = new Alert(AlertType.INFORMATION);
	    		infoPopup.setContentText("There is currently " + quantitySum + " food item in the pantry.");
	    		infoPopup.showAndWait();
	    	} else {
	    		Alert infoPopup = new Alert(AlertType.INFORMATION);
	    		infoPopup.setContentText("There are currently " + quantitySum + " food items in the pantry.");
	    		infoPopup.showAndWait();
	    	}
	    }
	    
	    @FXML
	    void setFood(ActionEvent event) {
	    	Food currentFood = this.pantry.getSelectionModel().getSelectedItem();
	    	try {
	    		int currentQuantity = Integer.parseInt(this.nextQuantity.getText());
	    		currentFood.setNextQuantity(currentQuantity);
	    		this.pantry.refresh();
	    	} catch (NumberFormatException quantityError) {
    			Utility.errorPopupGenerator("Unable to set quantity: ", " Please add a whole positive number to the box.", quantityError);
	    	} catch (IllegalArgumentException argumentError) {
	    		if (Integer.parseInt(this.nextQuantity.getText()) < 0) {
	    			Utility.errorPopupGenerator("Unable to set quantity: ", " Please add a whole positive number to the box.", argumentError);
	    		} else if (Integer.parseInt(this.nextQuantity.getText()) > Integer.MAX_VALUE) {
	    			Utility.errorPopupGenerator("Unable to set quantity: ", " Please add a smaller number to the box.", argumentError);
	    		}
	    	} catch (NullPointerException nullError) {
        		Utility.errorPopupGenerator("Unable to set quantity: \n", ".\n Please select a food to set the quantity of.", nullError);
	    	} 
	    }
	    
	    @FXML
	    void increaseFood(ActionEvent event) {
	    	Food currentFood = this.pantry.getSelectionModel().getSelectedItem();
	    	try {
	    		currentFood.incrementQuantity();
	    		this.pantry.refresh();
	    	} catch (IllegalStateException tooHighError) {
	    		Utility.errorPopupGenerator("Unable to increase quantity: ", " Cannot increase the quantity of food any higher.", tooHighError);
	    	} catch (NullPointerException nullError) {
	    		Utility.errorPopupGenerator("Unable to increase quantity: \n", ".\nPlease select a food to increase the quantity of.", nullError);
	    	} 
	    }
	    
	    @FXML
	    void decreaseFood(ActionEvent event) {
	    	Food currentFood = this.pantry.getSelectionModel().getSelectedItem();
	    	try {
	    		currentFood.decrementQuantity();
	    		this.pantry.refresh();
	    	} catch (IllegalStateException tooHighError) {
	    		Utility.errorPopupGenerator("Unable to decrease quantity: ", " Cannot decrease the quantity of food any lower.", tooHighError);
	    	} catch (NullPointerException nullError) {
	    		Utility.errorPopupGenerator("Unable to decrease quantity: \n", ".\nPlease select a food to decrease the quantity of.", nullError);
	    	} 
	    }
	    
	    @FXML
	    void initialize() {
	    	this.type.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient");
	    }

}

package edu.westga.cs1302.project_1.view;

import edu.westga.cs1302.project_1.model.Food;
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
	    			Alert errorPopup = new Alert(AlertType.ERROR);
	        		errorPopup.setContentText("Unable to add food: " + error.getMessage() + " Please add a name and type for the food you wish to add.");
	        		errorPopup.showAndWait();
	    		} else if (this.name.getText() == null || this.name.getText().isBlank()) {
	    			Alert errorPopup = new Alert(AlertType.ERROR);
	        		errorPopup.setContentText("Unable to add food: " + error.getMessage() + " Please add a name for the food you wish to add.");
	        		errorPopup.showAndWait();
	    		}  else if (this.type.getValue() == null) {
	    			Alert errorPopup = new Alert(AlertType.ERROR);
	        		errorPopup.setContentText("Unable to add food: " + error.getMessage() + " Please add a type for the food you wish to add.");
	        		errorPopup.showAndWait();
	    		}
	    	}
	    	
	    }
	    
	    @FXML
	    void setFood(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    void increaseFood(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    void decreaseFood(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    void initialize() {
	    	this.type.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient");
	    }

}

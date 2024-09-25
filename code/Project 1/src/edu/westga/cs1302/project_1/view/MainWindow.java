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
	    	
	    }
	    
	    @FXML
	    void setFood(ActionEvent event) {
	    	Food currentFood = this.pantry.getSelectionModel().getSelectedItem();
	    	try {
	    		int currentQuantity = Integer.parseInt(this.nextQuantity.getText());
	    		currentFood.setNextQuantity(currentQuantity);
	    		this.pantry.refresh();
	    	} catch (NumberFormatException quantityError) {
	    		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to set quantity: " + quantityError.getMessage() + ". Please add a whole positive number to the box.");
        		errorPopup.showAndWait();
	    	} catch (IllegalArgumentException argumentError) {
	    		if (Integer.parseInt(this.nextQuantity.getText()) < 0) {
	    			Alert errorPopup = new Alert(AlertType.ERROR);
	        		errorPopup.setContentText("Unable to set quantity: " + argumentError.getMessage() + " Please add a whole positive number to the box.");
	        		errorPopup.showAndWait();
	    		} else if (Integer.parseInt(this.nextQuantity.getText()) > Integer.MAX_VALUE) {
	    			Alert errorPopup = new Alert(AlertType.ERROR);
	        		errorPopup.setContentText("Unable to set quantity: " + argumentError.getMessage() + " Please add a smaller number to the box.");
	        		errorPopup.showAndWait();
	    		}
	    	} catch (NullPointerException nullError) {
	    		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to set quantity: " + nullError.getMessage() + ". Please select a food to set the quantity of.");
        		errorPopup.showAndWait();
	    	} 
	    }
	    
	    @FXML
	    void increaseFood(ActionEvent event) {
	    	Food currentFood = this.pantry.getSelectionModel().getSelectedItem();
	    	try {
	    		currentFood.incrementQuantity();
	    		this.pantry.refresh();
	    	} catch (IllegalStateException tooHighError) {
	    		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to increase quantity: " + tooHighError.getMessage() + " Cannot increase the quantity of food any higher.");
        		errorPopup.showAndWait();
	    	} catch (NullPointerException nullError) {
	    		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to increase quantity: " + nullError.getMessage() + ". Please select a food to increase the quantity of.");
        		errorPopup.showAndWait();
	    	} 
	    }
	    
	    @FXML
	    void decreaseFood(ActionEvent event) {
	    	Food currentFood = this.pantry.getSelectionModel().getSelectedItem();
	    	try {
	    		currentFood.decrementQuantity();
	    		this.pantry.refresh();
	    	} catch (IllegalStateException tooHighError) {
	    		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to decrease quantity: " + tooHighError.getMessage() + " Cannot decrease the quantity of food any lower.");
        		errorPopup.showAndWait();
	    	} catch (NullPointerException nullError) {
	    		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to decrease quantity: " + nullError.getMessage() + ". Please select a food to decrease the quantity of.");
        		errorPopup.showAndWait();
	    	} 
	    }
	    
	    @FXML
	    void initialize() {
	    	this.type.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient");
	    }

}

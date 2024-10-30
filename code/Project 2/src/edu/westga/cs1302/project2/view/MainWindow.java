package edu.westga.cs1302.project2.view;

import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.TypeComparator;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ComboBox<String> ingredientType;
	@FXML private ComboBox<Comparator<Ingredient>> sortBy;
	@FXML private ListView<Ingredient> ingredientsList;
	@FXML private ListView<?> recipeIngredients;
	@FXML private ListView<?> recipeStorage;
	@FXML private TextField recipeName;
	@FXML private TextField ingredientName;

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems().add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			ActionEvent sort = new ActionEvent();
			this.sortIngredient(sort);
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
		}
		ActionEvent sort = new ActionEvent();
		this.sortIngredient(sort);
	}
	
	@FXML
	void sortIngredient(ActionEvent event) {
		this.ingredientsList.getItems().sort(this.sortBy.getValue());
	}
	
	@FXML
	void initialize() {
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");
		this.sortBy.getItems().add(new TypeComparator());
		this.sortBy.getItems().add(new NameComparator());
		this.sortBy.setValue(this.sortBy.getItems().get(0));
	}
}

package edu.westga.cs1302.project2.view;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.project2.model.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeDisplayManager;
import edu.westga.cs1302.project2.model.RecipePersistenceManager;
import edu.westga.cs1302.project2.model.TypeComparator;
import edu.westga.cs1302.project2.model.Utility;

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
	@FXML private ListView<Ingredient> recipeIngredients;
	@FXML private TextField recipeName;
	@FXML private TextField ingredientName;
	@FXML private TextArea recipeDisplay;

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
	void addRecipeIngredient(ActionEvent event) {
		try {
		if (this.ingredientsList.getSelectionModel().getSelectedItem() == null) {
			throw new IllegalStateException("No ingredient selected.");
		}
		this.recipeIngredients.getItems().add(this.ingredientsList.getSelectionModel().getSelectedItem());
		} catch (IllegalStateException noneSelected) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient to recipe");
			alert.setContentText(noneSelected.getMessage());
			alert.showAndWait();
		}
	}
	
	@FXML
	void saveRecipe(ActionEvent event) {
		try {
		String formattedRecipe = Utility.formatRecipe(this.recipeName.getText(), this.recipeIngredients.getItems());
		RecipePersistenceManager saver = new RecipePersistenceManager();
		saver.saveRecipeData(formattedRecipe);
		} catch (IllegalArgumentException badInput) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add recipe");
			alert.setContentText(badInput.getMessage());
			alert.showAndWait();
		} catch (IOException writingError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add recipe");
			alert.setContentText(writingError.getMessage());
			alert.showAndWait();
		} catch (IllegalStateException namingError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add recipe");
			alert.setContentText(namingError.getMessage());
			alert.showAndWait();
		}
		this.recipeIngredients.getItems().clear();
		this.recipeName.clear();
	}
	
	@FXML
	void displayRecipes(ActionEvent event) {
		try {
			Ingredient filterIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
			if (filterIngredient == null) {
				throw new IllegalStateException("No ingredient selected.");
			}	
			List<Recipe> filteredRecipes = RecipeDisplayManager.filterRecipes(filterIngredient);
			String displayText = Utility.formatDisplayRecipes(filteredRecipes);
			this.recipeDisplay.setText(displayText);
		} catch (IllegalStateException noneSelected) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to display recipes");
			alert.setContentText(noneSelected.getMessage());
			alert.showAndWait();
		} catch (IOException loadingError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to display recipes");
			alert.setContentText(loadingError.getMessage());
			alert.showAndWait();
		}
		
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

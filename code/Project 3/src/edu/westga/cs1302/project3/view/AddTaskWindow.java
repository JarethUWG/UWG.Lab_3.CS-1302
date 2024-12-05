package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
	@FXML private Button addTaskButton;
	@FXML private Button cancel;
	@FXML private AnchorPane addPane;
    @FXML private TextField taskTitle;
	@FXML private TextArea taskDescription;	
	
	@FXML
	void initialize() {
		this.cancel.setOnAction((event) -> {
			Stage stage = (Stage) (this.addPane).getScene().getWindow();
			stage.close();
		});
	}
	
	/** Binds a the properties of a view to appropriate 
	 * local properties and manages events related to vm.
	 * 
	 * @param vm the ViewModel to bind
	 */
	public void viewModelManagement(ViewModel vm) {
		vm.getTaskTitle().bind(this.taskTitle.textProperty());
		vm.getTaskDescription().bind(this.taskDescription.textProperty());
		this.addTaskButton.setOnAction((event) -> {
			try {
				vm.addNextTask();
				Stage stage = (Stage) (this.addPane).getScene().getWindow();
				stage.close();
			} catch (IllegalArgumentException badInput) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(badInput.getMessage());
				alert.showAndWait();
			} catch (IllegalStateException badInput) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(badInput.getMessage());
				alert.showAndWait();
			}
		});
	}

}

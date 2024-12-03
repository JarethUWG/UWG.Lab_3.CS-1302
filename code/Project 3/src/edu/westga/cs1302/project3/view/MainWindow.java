package edu.westga.cs1302.project3.view;

import java.io.File;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ListView<Task> taskDisplay;
	@FXML private MenuItem menuLoadTask;
	@FXML private AnchorPane mainPane;
	
	 private ViewModel vm;
	 private ObjectProperty<File> loadFile;
	
	 @FXML
	 void initialize() {
		this.vm = new ViewModel();
		this.loadFile = new SimpleObjectProperty<File>();
		this.taskDisplay.itemsProperty().bindBidirectional(this.vm.getTasks());
		this.loadFile.bindBidirectional(this.vm.getLoadFile());
		this.menuLoadTask.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image File");
			fileChooser.getExtensionFilters().addAll(
			new ExtensionFilter("All Files", "*.*"));
			Stage stage = (Stage) this.mainPane.getScene().getWindow();
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				this.loadFile.set(selectedFile);
				if (!this.vm.loadFileSelector()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Selected File is improperly formatted");
					alert.showAndWait();
				} else {
					this.vm.loadFileSelector();
				}
			}
		});
	}
}

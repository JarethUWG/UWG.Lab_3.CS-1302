package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private TextArea output;
    
    private ViewModel viewModel;

    /**
	 * Instantiates a new MainWindow.
	 * 
	 * @precondition none
	 * @precondition none
	 */
    public MainWindow() {
		this.viewModel = new ViewModel();
	}

    @FXML
    void initialize() {
        assert this.mustIncludeDigits != null : "fx:id=\"mustIncludeDigits\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.mustIncludeLowerCaseLetters != null : "fx:id=\"mustIncludeLowerCaseLetters\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.mustIncludeUpperCaseLetters != null : "fx:id=\"mustIncludeUpperCaseLetters\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.minimumLength != null : "fx:id=\"minimumLength\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.output != null : "fx:id=\"output\" was not injected: check your FXML file 'MainWindow.fxml'.";
        this.minimumLength.setText("1");
        this.bindControlsToViewModel();
    }
    
    private void bindControlsToViewModel() {
    	this.mustIncludeDigits.selectedProperty().bindBidirectional(this.viewModel.digitsProperty());
    	this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(this.viewModel.lowerProperty());
    	this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(this.viewModel.upperProperty());
		this.minimumLength.textProperty().bindBidirectional(this.viewModel.lengthProperty());
		this.output.textProperty().bindBidirectional(this.viewModel.outputProperty());
	}
    
    @FXML
	private void handleGeneratePassword() {
		this.viewModel.viewModelGeneratePassword();
	}
}

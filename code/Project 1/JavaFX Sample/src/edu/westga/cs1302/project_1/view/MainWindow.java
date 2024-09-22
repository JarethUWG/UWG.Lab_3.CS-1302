package edu.westga.cs1302.project_1.view;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

import javafx.scene.control.ListView;

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
	    private ListView<?> pantry;

	    @FXML
	    private ComboBox<?> type;

	    @FXML
	    void addFood(ActionEvent event) {
	    	
	    }

}
